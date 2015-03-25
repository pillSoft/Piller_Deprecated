# Piller
First commit for Piller,a Cyanogenmod Theme Installer for Lollipop

Piller is an installer of themes, open source (GPL license), developed by pillSoft ([Giulio Fagioli](http://goo.gl/eBEyMb) e [Lorenzo Salani](http://goo.gl/zARfDu) ).
This Theme installer uses a library provided by [Ishida](https://github.com/ishida/android-easy-lvl) to control the playstore license, uses pieces of code provided by [Rahul K Dinesh](https://github.com/b16h22/Theme-Installer), for the installation of the themes.

####Tutorial Step by Step:
[XDA](http://forum.xda-developers.com/showpost.php?p=59672314&postcount=15)

####For users:
To add a theme to the installer must perform the following steps:
- Addition of the theme in assets / files, the name of 'apk must be formed only by the name of the theme **Important**.
- Changing the information of the theme in the file arrays.xml this includes:
* Theme Name
* Package Theme
* Motto of the theme
* Color of Primary Theme
* Primary Color of Dark Theme
* Primary Accent Color of Theme
* Highlighted Primary Color of Theme

####Add your Application public key in EasyLicenseChecker.java , Get it on Developer Console
`private static final String BASE64_PUBLIC_KEY = "REPLACE THIS WITH YOUR PUBLIC KEY";`

####For disable license checker comment this line in MainActivity.java , For default is Enabled
`mLicenseChecker.start();`

####Change The name of application
```
<string name = "nameApplication"> Piller </ string>
<string name = "app_name"> Piller </ string>
```

####Customize Toolbar and colors refers to the file Color.xml as:
```
<color name = "ColorPrimary"> # 2d5d82 </ color> ToolBar
<color name = "colorPrimaryDark"> # 21425d </ color> StatusBar
<color name = "colorAccent"> # 01bcd5 </ color> AccentColor
```

To insert the image of the subject that will appear in the installation window should change some lines of code (look for "Change it for image Theme"):
```
switch (auxTheme.theme_name) {
case "Lin": // Replace Lin with your Theme Name
res = R.drawable.imm; // Replace imm with your image name
break;
case "Daa": // Replace Lin with your Theme Name
res = R.drawable.imm2; // Replace imm with your image name
}
```

Also provided are also files that relate to the themes and images placeholder icon, these files can be opened with illustrator,**Located in Graphics Folder**

