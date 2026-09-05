package com.applovin.impl.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j {
    private static final List ap = Arrays.asList(Boolean.class, Float.class, Integer.class, Long.class, String.class);
    private static final List aq = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f204a = a("is_disabled", false);
    public static final l b = a("should_load_pub_settings", true);
    public static final l c = a("device_id", "");
    public static final l d = a("publisher_id", "");
    public static final l e = a("device_token", "");
    public static final l f = a("init_retry_count", 1);
    public static final l g = a("submit_data_retry_count", 1);
    public static final l h = a("vr_retry_count", 1);
    public static final l i = a("fetch_ad_retry_count", 1);
    public static final l j = a("is_verbose_logging", false);
    public static final l k = a("api_endpoint", "http://d.applovin.com/");
    public static final l l = a("adserver_endpoint", "http://a.applovin.com/2.0/");
    public static final l m = a("next_device_init", 0L);
    public static final l n = a("get_retry_delay", 10000L);
    public static final l o = a("max_apps_to_send", 100);
    public static final l p = a("is_app_list_shared", true);
    public static final l q = a("next_app_list_update", 0L);
    public static final l r = a("hash_algorithm", "SHA-1");
    public static final l s = a("short_hash_size", 16);
    public static final l t = a("http_connection_timeout", 30000);
    public static final l u = a("fetch_ad_connection_timeout", 30000);
    public static final l v = a("http_socket_timeout", 20000);
    public static final l w = a("error_save_count", 15);
    public static final l x = a("ad_session_minutes", 60);
    public static final l y = a("ad_request_parameters", "");
    public static final l z = a("ad_refresh_enabled", true);
    public static final l A = a("ad_refresh_seconds", 120L);
    public static final l B = a("mrec_ad_refresh_enabled", true);
    public static final l C = a("mrec_ad_refresh_seconds", 120L);
    public static final l D = a("leader_ad_refresh_enabled", true);
    public static final l E = a("leader_ad_refresh_seconds", 120L);
    public static final l F = a("plugin_version", "");
    public static final l G = a("ad_preload_enabled", true);
    public static final l H = a("ad_resource_caching_enabled", true);
    public static final l I = a("resource_cache_prefix", "http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    public static final l J = a("ad_auto_preload_sizes", "BANNER,INTER");
    public static final l K = a("ad_auto_preload_incent", true);
    public static final l L = a("session_expiration_time", 300L);
    public static final l M = a("track_installed_apps", true);
    public static final l N = a("is_tracking_enabled", true);
    public static final l O = a("force_back_button_enabled", false);
    public static final l P = a("is_first_install", "unknown");
    public static final l Q = a("countdown_direction", "right_to_left");
    public static final l R = a("countdown_color", "#C8FFFFFF");
    public static final l S = a("countdown_height", 2);
    public static final l T = a("close_fade_in_time", 400);
    public static final l U = a("draw_countdown_text", true);
    public static final l V = a("draw_countdown_bar", true);
    public static final l W = a("show_close_on_exit", true);
    public static final l X = a("text_incent_prompt_title", "Earn a Reward");
    public static final l Y = a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    public static final l Z = a("text_incent_prompt_yes_option", "Watch Now");
    public static final l aa = a("text_incent_prompt_no_option", "No Thanks");
    public static final l ab = a("text_incent_completion_title", "Video Reward");
    public static final l ac = a("text_incent_completion_body_success", "You have earned a reward!");
    public static final l ad = a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    public static final l ae = a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    public static final l af = a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    public static final l ag = a("text_incent_completion_close_option", "Okay");
    public static final l ah = a("show_incent_prepopup", true);
    public static final l ai = a("show_incent_postpopup", true);
    public static final l aj = a("preload_capacity_banner", 1);
    public static final l ak = a("preload_capacity_mrec", 1);
    public static final l al = a("preload_capacity_inter", 1);
    public static final l am = a("preload_capacity_leader", 1);
    public static final l an = a("preload_capacity_incent", 2);
    public static final l ao = a("dismiss_video_on_error", true);

    private static l a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        }
        if (!ap.contains(obj.getClass())) {
            throw new IllegalArgumentException("Unsupported value type: " + obj.getClass());
        }
        l lVar = new l(str, obj);
        aq.add(lVar);
        return lVar;
    }

    public static Collection a() {
        return Collections.unmodifiableList(aq);
    }

    public static int b() {
        return aq.size();
    }
}
