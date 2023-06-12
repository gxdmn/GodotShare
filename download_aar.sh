#!/bin/bash

url="https://downloads.tuxfamily.org/godotengine/4.0/godot-lib.4.0.stable.template_release.aar"
outputDir="GodotShare/libs"

cd "$outputDir" 

wget "$url"

echo "Download completed"
