# WinApps
WinDesktopApps

    Download and install the latest WinAppDriver from https://github.com/Microsoft/WinAppDriver/releases
    Enable Developer Mode in Windows settings
    Run WinAppDriver.exe (eg from C:\Program Files\Windows Application Driver)

Additional info: https://github.com/microsoft/WinAppDriver; https://www.browserstack.com/guide/test-windows-desktop-app-using-appium-winappdriver

Description of the tests:

Calculator - Simple automation of basic mathematical operations, eg 2+2 Excel - Launches Microsoft Excel and writes data and mathematical formula into the cells Jsch - Simple logging to a linux server via ssh protocol, passing a few commands and output verification Notepad - Launches Notepad and sends a string OutlookOldTest - Automated sending of a mail, containing a receptient, cc, subject, attachment and body text using virtual keyboard OutlookTests - Automated sending of a mail, containing a receptient, cc, subject, attachment and body text using Action class in Java

POM version of OutlookTests:

*baseUtilities src/main/java/baseUtilities *outlook src/main/java/outlook *outlookTests src/test/java/outlookTests

Cucumber version of OutlookTests: *feature file: features/Outlook.feature *step Implementations: stepImplementationsMail/OutlookTest.java *Outlook runner class: cucumberTests/OutlookRunner.java

Putty - Automated setting up of the putty client, logging in to a server, sending a few commands and verification of the output. Also contains the cucumber feature file

POM version of PuTTY: Base Utilities: src/main/java/baseUtilitiesPuTTY Page Objects: src/main/java/puTTYPageObjectModel Tests: src/test/java/puTTYTest

TestMaps - Test taken from https://www.browserstack.com/guide/test-windows-desktop-app-using-appium-winappdriver WinSCP - Logging to a linux server, and copying of a file to /home/user/ directory using the virtual keyboard WinSCP2 - Logging to a linux server, and copying of a file to /home/user/ directory using Action class in Java (not finished) Word - Opens a Word document and sends a string

*For the locators of the desktop applications, use the following software: UISpy.exe Inspect.exe Accessibility Insights For Windows UI recorder: https://github.com/Microsoft/WinAppDriver/wiki/WinAppDriver-UI-Recorder Download the whole package and run WinAppDriverUIRecorder.sln in Visual Studio code or Visual Studio (recommended). The latest .net SDK is recommended.
