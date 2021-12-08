# kard

The Flutter GUI front end for kard.

This directory is an IntelliJ project using the [Flutter](https://flutter.dev) framework written in [Dart](https://dart.dev). 

Below are details on how to configure both an Android emulator or an iOs iPhone simulator for flutter development:

# Flutter Project Installation Instructions

Before opening the kard GUI, you must follow the instructions below to set up your computer for Flutter development.

## Install Flutter

Navigate to the [flutter install page](https://flutter.dev/docs/get-started/install) and follow the instructions to install the Flutter SDK for your specific operating system.

### Install Plugins

Open IntelliJ. Go to `Preferences` and navigate to 'Plugins'

Install [Dart](https://plugins.jetbrains.com/plugin/6351-dart) plugin for Dart language support

Install [Flutter](https://plugins.jetbrains.com/plugin/9212-flutter) plugin for Flutter development support

These can be found by clicking on "Marketplace" and searching for "Dart" and "Flutter"

![installation_19](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_19.png)

![installation_21](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_21.png)

With the above plugins installed, you should be able to open the `kard_project_test` project in IntelliJ, however to test the code you must install an emulation environment or allow USB Debugging on your Android Phone. You may have to set the path for your flutter install for IntelliJ. You should be prompted to do this by IntelliJ if needed.

## Android Studio

Download and install [Android Studio](https://developer.android.com/studio) for the Android Emulator. Once installed, you will be presented with the following page.

Click the three dots next to the "Get from VCS" button in the top right and select "SDK Manager"

![installation_3](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_3.png)

![installation_6](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_6.png)

Select "SDK Tools" and ensure that:

- Android SDK Command-line Tools (latest)
- Android Emulator
- Android SDK Platform-Tools

Are selected and installed.

![installation_5](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_5.png)

Press "OK" and return to the Android Studio Home Screen. Once again, click on the three dot menu and select AVD Manager.

![installation_8](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_8.png)

Select "Create Virtual Device" on the bottom left

![installation_18](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_18.png)

Select Pixel 3a in the menu that is pulled up. Once selected, click on "Next"

![installation_9](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_9.png)

Download "Android R" System Image and select it before clicking next.

![installation_17](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_17.png)

Finally, select finish

![installation_7](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_7.png)

Once the virtual device is created, select the green play button to start the Android Emulator

![installation_16](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_16.png)

When launched, the virtual device should look similar to this

![installation_2](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_2.png)

Once launched, open the kard_project_test project in IntelliJ. You will be able to select the open Android emulator for a test device.

![installation_15](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_15.png)

Once the device is selected, you can run the project by pressing the green play button shown below. This will start a build script and launch the app automatically on the open emulator. This can take up to a minute.

![installation_12](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_12.png)

Once launched, the app should look like this. Please note that you **must** have [kard-server]() running for this app to function.

![installation_20](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_20.png)

## Xcode

To run the program on an iPhone emulator, you must use and Apple computer and install [Xcode from the App Store](https://apps.apple.com/us/app/xcode/id497799835?mt=12) 

Once installed, install `cocoapods`. If you use Homebrew, this can be accomplished with `brew install cocoapods`.

Once completed, run the following two commands to prepare the Xcode simulator:

```bash
sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer
sudo xcodebuild -runFirstLaunch
```

Finally use spotlight search (CMD + [space]) and search simulator. Launch the app. This will open an iPhone simulator. Once open is should look like this:

![installation_10](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_10.png)

With the simulator open, like with Android, you also open the IntelliJ project: kard_project_test and select the device to launch the app on in the drop down menu as shown below.

![installation_13](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_13.png)

Once selected, you can click the green play button and IntelliJ will automatically choose Xcode to build the project for the iPhone. Once the build is completed, the app will be automatically loaded onto the iPhone simulator. It should look the same as the screenshot shown below. Please note that you **must** have [kard-server]() running for the app to function.

![installation_1](/Users/arthurgao/Documents/Presentation_Code/course-project-purplemongoose/kard/Installation_Instructions.assets/installation_1.png)

### Some tips

If you encounter any errors while running the project, a quick debugging method is in your command line interface ie. terminal, run `flutter doctor`. It is often able to catch issues with your install and provide methods to fix it.

If you have any issues with any of the steps in this document please contact Arthur Gao at: arthur.gao@mail.urotonto.ca



