#include "log.h"

#include <psp2/kernel/clib.h>
#include <psp2/kernel/processmgr.h>
#include <psp2/kernel/threadmgr.h>
#include <psp2/io/fcntl.h>
#include <psp2/io/dirent.h>
#include <psp2/io/stat.h>
#include <psp2/net/net.h>
#include <psp2/net/netctl.h>
#include <psp2/sysmodule.h>

#include <stdio.h>
#include <string.h>

#ifndef BR_LOG_HOST
#define BR_LOG_HOST "192.168.1.149"   /* overridden by -DBR_LOG_HOST in CMake */
#endif
#ifndef BR_LOG_PORT
#define BR_LOG_PORT 18194
#endif

#define NET_HEAP_SIZE (1 * 1024 * 1024)
#define LINE_MAX      1024

static char s_net_heap[NET_HEAP_SIZE] __attribute__((aligned(16)));
static int  s_sock = -1;
static SceUID s_file = -1;
static SceUID s_mutex = -1;
static int  s_net_ready = 0;
static struct SceNetSockaddrIn s_unicast;
static struct SceNetSockaddrIn s_broadcast;

static void open_logfile(void)
{
    sceIoMkdir("ux0:data", 0777);
    sceIoMkdir("ux0:data/bikerace", 0777);
    /* Truncate on boot: each run starts a fresh on-device log. */
    s_file = sceIoOpen("ux0:data/bikerace/debug.log",
                       SCE_O_WRONLY | SCE_O_CREAT | SCE_O_TRUNC, 0777);
}

static void open_socket(void)
{
    SceNetInitParam param;
    int ret;

    ret = sceSysmoduleLoadModule(SCE_SYSMODULE_NET);
    if (ret < 0)
        return;

    param.memory = s_net_heap;
    param.size   = NET_HEAP_SIZE;
    param.flags  = 0;

    ret = sceNetInit(&param);
    /* SCE_NET_ERROR_EBUSY means someone already initialised the stack, which
     * is fine -- we can still create a socket. */
    if (ret < 0 && ret != (int)0x80410108)
        return;

    sceNetCtlInit();

    s_sock = sceNetSocket("br_log", SCE_NET_AF_INET, SCE_NET_SOCK_DGRAM, 0);
    if (s_sock < 0) {
        s_sock = -1;
        return;
    }

    {
        int on = 1;
        sceNetSetsockopt(s_sock, SCE_NET_SOL_SOCKET, SCE_NET_SO_BROADCAST,
                         &on, sizeof(on));
    }

    memset(&s_unicast, 0, sizeof(s_unicast));
    s_unicast.sin_family = SCE_NET_AF_INET;
    s_unicast.sin_port   = sceNetHtons(BR_LOG_PORT);
    sceNetInetPton(SCE_NET_AF_INET, BR_LOG_HOST, &s_unicast.sin_addr);

    /* Broadcast as well, so a changed PC IP does not silence the log. */
    memset(&s_broadcast, 0, sizeof(s_broadcast));
    s_broadcast.sin_family = SCE_NET_AF_INET;
    s_broadcast.sin_port   = sceNetHtons(BR_LOG_PORT);
    sceNetInetPton(SCE_NET_AF_INET, "255.255.255.255", &s_broadcast.sin_addr);

    s_net_ready = 1;
}

void br_log_init(void)
{
    s_mutex = sceKernelCreateMutex("br_log", 0, 0, NULL);
    open_logfile();
    open_socket();

    LOGI("=== Bike Race Vita ===");
    LOGI("build %s %s", __DATE__, __TIME__);
    LOGI("log target %s:%d (net_ready=%d, file=%d)",
         BR_LOG_HOST, BR_LOG_PORT, s_net_ready, (int)(s_file >= 0));
}

void br_log_write(const char *level, const char *file, int line, const char *fmt, ...)
{
    char body[LINE_MAX];
    char out[LINE_MAX + 128];
    const char *base;
    va_list ap;
    int n;

    va_start(ap, fmt);
    vsnprintf(body, sizeof(body), fmt, ap);
    va_end(ap);

    base = strrchr(file, '/');
    base = base ? base + 1 : file;

    n = snprintf(out, sizeof(out), "[%8u][%s] %s:%d: %s\n",
                 (unsigned)(sceKernelGetProcessTimeWide() / 1000),
                 level, base, line, body);
    if (n < 0)
        return;
    if (n > (int)sizeof(out) - 1)
        n = (int)sizeof(out) - 1;

    if (s_mutex >= 0)
        sceKernelLockMutex(s_mutex, 1, NULL);

    /* Always mirror to the PSVita's own debug channel (visible in PrincessLog
     * / psp2shell) and to the on-device file. */
    sceClibPrintf("%s", out);
    if (s_file >= 0)
        sceIoWrite(s_file, out, n);

    if (s_net_ready) {
        sceNetSendto(s_sock, out, n, 0,
                     (struct SceNetSockaddr *)&s_unicast, sizeof(s_unicast));
        sceNetSendto(s_sock, out, n, 0,
                     (struct SceNetSockaddr *)&s_broadcast, sizeof(s_broadcast));
    }

    if (s_mutex >= 0)
        sceKernelUnlockMutex(s_mutex, 1);
}

void br_log_flush(void)
{
    if (s_file >= 0)
        sceIoSyncByFd(s_file, 0);
}

void br_log_shutdown(void)
{
    br_log_flush();
    if (s_file >= 0) {
        sceIoClose(s_file);
        s_file = -1;
    }
    if (s_sock >= 0) {
        sceNetSocketClose(s_sock);
        s_sock = -1;
    }
    if (s_mutex >= 0) {
        sceKernelDeleteMutex(s_mutex);
        s_mutex = -1;
    }
    s_net_ready = 0;
}
