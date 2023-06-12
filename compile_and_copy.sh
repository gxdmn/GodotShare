#!/bin/bash

projectPath="GodotShare"
outputPath="plugin"

./gradlew build -p "$projectPath"

cp "$projectPath/build/outputs/aar/"*release.aar "$outputPath"

echo "Build completed successfully."
