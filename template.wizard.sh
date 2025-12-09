#!/bin/sh

# Template wizard script to rename project package and paths.
if [ $# -ne 2 ]; then
    echo "Usage: $0 old.package new.package"
    exit 1
fi

# Store arguments
OLD_PACKAGE="$1"
NEW_PACKAGE="$2"

# Basic validation of package names (very simple heuristic)
case "$OLD_PACKAGE" in *..*|.*|*.) echo "Invalid OLD_PACKAGE: $OLD_PACKAGE" >&2; exit 1;; esac
case "$NEW_PACKAGE" in *..*|.*|*.) echo "Invalid NEW_PACKAGE: $NEW_PACKAGE" >&2; exit 1;; esac

# Convert package → directory path
OLD_PATH=$(echo "$OLD_PACKAGE" | tr '.' '/')
NEW_PATH=$(echo "$NEW_PACKAGE" | tr '.' '/')

# Escape dots for sed regex
OLD_PACKAGE_REGEX=$(printf "%s" "$OLD_PACKAGE" | sed 's/\./\\./g')

# Cross-platform sed -i flag
sed_inplace() {
    if sed --version >/dev/null 2>&1; then
        sed -i "$@"
    else
        sed -i '' "$@"
    fi
}

echo "Step 1: Renaming project directories"
find . -type d -path "*/src/*/$OLD_PATH*" | while read -r DIR; do
    NEW_DIR=$(echo "$DIR" | sed "s|$OLD_PATH|$NEW_PATH|g")

    mkdir -p "$NEW_DIR" || { echo "Failed to create $NEW_DIR" >&2; continue; }

    for ITEM in "$DIR"/*; do
        [ -e "$ITEM" ] || continue
        [ "$ITEM" = "$NEW_DIR" ] && continue
        mv "$ITEM" "$NEW_DIR"/ || echo "Failed to move $ITEM → $NEW_DIR" >&2
    done

    CURRENT="$DIR"
    while rmdir "$CURRENT" 2>/dev/null; do
        CURRENT=$(dirname "$CURRENT")
        case "$CURRENT" in */src) break ;; esac
    done
done

echo "Step 2: Updating Kotlin files"
find . -path "*/src/*" -type f -name "*.kt" | while read -r FILE; do

    # update package
    sed_inplace "s|^package $OLD_PACKAGE_REGEX|package $NEW_PACKAGE|" "$FILE"

    # update imports
    sed_inplace "s|^import $OLD_PACKAGE_REGEX|import $NEW_PACKAGE|" "$FILE"
done

echo "Step 3: Updating Gradle files"
find . -type f -name "*.kts" | while read -r FILE; do

    # replace ANY declaration
    sed_inplace "s|$OLD_PACKAGE_REGEX|$NEW_PACKAGE|g" "$FILE"
done

echo "Template wizard job finished."
