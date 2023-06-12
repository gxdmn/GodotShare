# Prerequisites
Make sure you can export and run the current Godot project on an Android device.
## Download Official Godot Android Library File
Download the `godot-lib.4.0.stable.template_release.aar` file from [here](https://downloads.tuxfamily.org/godotengine/4.0/godot-lib.4.0.stable.template_release.aar) and copy that file inside the `GodotShare/libs` directory.

#### OR

Run the `./download_aar.sh` file on your terminal. The script will download the necessary file and copy it to the `libs` folder.

## Compile Plugin
Run the `compile_and_copy.sh` file on your terminal:

```bash
./compile_and_copy.sh
```

## Copy Files
Copy the generated files `*.gdap` and `*.aar` from the `plugin` directory into your Godot project's `android/plugins` directory.

## Activate Plugin in Godot
Enable the plugin by going to `Project` → `Export` → `(Android Project)` → `Plugins` and check the "Godot Share" checkbox.

## Use Plugin
Use the following code in your Godot project's `_ready` function:

```gdscript
if Engine.has_singleton("GodotShare"):
    var gshare = Engine.get_singleton("GodotShare")
    # This is a test function to check if the plugin is working correctly or not.
    var result = gshare.function_demo(101) # should return 202
    $Label.text = str(result)
```
## API
```
// path -> path location respective to "user://" directory. If you want to share user://godot.png", pass "godot.png" as path
share_img(path, title, message)
share_img_web(url, title, message)
```