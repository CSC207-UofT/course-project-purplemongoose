

# Flutter Project Installation Instructions

Before opening the kard GUI, you must follow the instructions below to set up your computer for Flutter development.

## Install Flutter

Navigate to the [flutter install page](https://flutter.dev/docs/get-started/install) and follow the instructions to install the Flutter SDK for your specific operating system.

### Install Plugins

Open IntelliJ. Go to `Preferences` and navigate to 'Plugins'

Install [Dart](https://plugins.jetbrains.com/plugin/6351-dart) plugin for Dart language support

Install [Flutter](https://plugins.jetbrains.com/plugin/9212-flutter) plugin for Flutter development support

These can be found by clicking on "Marketplace" and searching for "Dart" and "Flutter"

![Screen Shot 2021-11-06 at 12.05.42 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.05.42 AM.png)

![Screen Shot 2021-11-06 at 12.06.27 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.06.27 AM.png)

With the above plugins installed, you should be able to open the `kard_project_test` project in IntelliJ, however to test the code you must install an emulation environment or allow USB Debugging on your Android Phone. You may have to set the path for your flutter install for IntelliJ. You should be prompted to do this by IntelliJ if needed.

## Android Studio

Download and install [Android Studio](https://developer.android.com/studio) for the Android Emulator. Once installed, you will be presented with the following page.

![Screen Shot 2021-11-06 at 12.07.25 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.07.25 AM.png)

Click the three dots next to the "Get from VCS" button in the top right and select "SDK Manager"

![Screen Shot 2021-11-06 at 12.09.12 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.09.12 AM.png)

Select "SDK Tools" and ensure that:

- Android SDK Command-line Tools (latest)
- Android Emulator
- Android SDK Platform-Tools

Are selected and installed.

![Screen Shot 2021-11-06 at 12.10.12 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.10.51 AM.png)

Press "OK" and return to the Android Studio Home Screen. Once again, click on the three dot menu and select AVD Manager.

![Screen Shot 2021-11-06 at 12.12.54 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.12.54 AM.png)

Select "Create Virtual Device" on the bottom left

![Screen Shot 2021-11-06 at 12.14.02 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.14.02 AM.png)

Select Pixel 3a in the menu that is pulled up. Once selected, click on "Next"

![Screen Shot 2021-11-06 at 12.14.56 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.14.56 AM.png)

Download "Android R" System Image and select it before clicking next.

![Screen Shot 2021-11-06 at 12.15.47 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.15.47 AM.png) 

Finally, select finish

![Screen Shot 2021-11-06 at 12.16.45 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.16.45 AM.png)

Once the virtual device is created, select the green play button to start the Android Emulator

![Screen Shot 2021-11-06 at 12.18.55 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.18.55 AM.png)

When launched, the virtual device should look similar to this

![Screen Shot 2021-11-06 at 12.22.02 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.22.02 AM.png)

Once launched, open the kard_project_test project in IntelliJ. You will be able to select the open Android emulator for a test device.

![Screen Shot 2021-11-06 at 12.24.00 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.24.00 AM.png)

Once the device is selected, you can run the project by pressing the green play button shown below. This will start a build script and launch the app automatically on the open emulator. This can take up to a minute.

![Screen Shot 2021-11-06 at 12.24.13 AM](../../Screenshots/Screen Shot 2021-11-06 at 12.25.26 AM.png)

Once launched, the app should look like this. Please note that you **must** have [kard-server]() running for this app to function.

## Xcode

To run the program on an iPhone emulator, you must use and Apple computer and install [Xcode from the App Store](https://apps.apple.com/us/app/xcode/id497799835?mt=12) 

Once installed, install `cocoapods`. If you use Homebrew, this can be accomplished with `brew install cocoapods`.

Once completed, run the following two commands to prepare the Xcode simulator:

```bash
sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer
sudo xcodebuild -runFirstLaunch
```

Finally use spotlight search (CMD + [space]) and search simulator. Launch the app. This will open an iPhone simulator. Once open is should look like this:

![Screen Shot 2021-11-06 at 12.40.10 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.40.10 AM.png)

With the simulator open, like with Android, you also open the IntelliJ project: kard_project_test and select the device to launch the app on in the drop down menu as shown below.

![Screen Shot 2021-11-06 at 12.41.41 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.41.41 AM.png)

Once selected, you can click the green play button and IntelliJ will automatically choose Xcode to build the project for the iPhone. Once the build is completed, the app will be automatically loaded onto the iPhone simulator. It should look the same as the screenshot shown below. Please note that you **must** have [kard-server]() running for the app to function.

![Screen Shot 2021-11-06 at 12.44.15 AM](https://github.com/CSC207-UofT/course-project-purplemongoose/tree/main/kard_project_test/Installation_Instructions.assets/Screen Shot 2021-11-06 at 12.44.15 AM.png)

### Some tips

If you encounter any errors while running the project, a quick debugging method is in your command line interface ie. terminal, run `flutter doctor`. It is often able to catch issues with your install and provide methods to fix it.

If you have any issues with any of the steps in this document please contact Arthur Gao at: arthur.gao@mail.urotonto.ca

