#ifndef BR_FS_H
#define BR_FS_H

#include <stdio.h>

/* Where the game's converted assets live on the Vita. Produced by
 * scripts/extract_assets.sh and copied over with scripts/deploy.sh. */
const char *br_asset_root(void);

/* Puts a freshly written ".tmp" in place of the file it replaces.
 *
 * Save data is written to a sibling temporary first and swapped in only once
 * it is whole, so a power cut mid-write cannot leave a half-file behind -- and
 * a half-file is indistinguishable from a first run to every loader here.
 *
 * This is not rename(2): sceIoRename refuses to overwrite an existing
 * destination, so the old file has to be removed first. That leaves a window
 * of a few microseconds in which neither name resolves; br_fs_open_saved()
 * closes it by falling back to the temporary, which is already complete by the
 * time the window opens. */
int br_fs_replace(const char *tmp, const char *path);

/* Opens a saved file for reading, taking the leftover temporary when `path`
 * itself is absent because a replace was interrupted. NULL when there is
 * nothing to read, which is what a first run looks like. */
FILE *br_fs_open_saved(const char *path, const char *tmp);

#endif /* BR_FS_H */
