# ReleaseAnimal
ReleaseAnimal is an android library to display release notes dialog without pain.

![Screenshot](screenshot.png)

# Installation
Append the code below in your `build.gradle`.

    compile 'itmammoth.releaseanimal:ReleaseAnimal:0.0.3'

# Usage

All you have to do is to create `res/xml/releaseanimal.xml` in your project directory.

    <?xml version="1.0" encoding="utf-8"?>
    <releaseNotes>
        <note date="2016-11-20" versionName="0.0.0.2">
            <message>- New awesome features!</message>
            <message>- Small bug fixes</message>
        </note>
        <note date="2016-11-25" versionName="0.0.1.0">
            <message>- Foo bar yeah!</message>
            <message>- Pen pineapple apple pen!</message>
            <message>- Blah blah blah</message>
        </note>
    </releaseNotes>

- ReleaseAnimal never shows notes that have already been shown to users again.
- ReleaseAnimal never shows notes that had been released before the user installed your app.
- ReleaseAnimal never shows notes that have future `date`.
- Add attribute `force="true"` to force to show a note.

# Configuration

If you need to change the release notes dialog title and close button's caption, create string resource for it.

`res/values/releaseanimal.xml`

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <string name="releaseanimal_dialog_title">Update info</string>
        <string name="releaseanimal_close">Got it</string>
    </resources>

# Contribution

Fork it, and send me a pull-request.

# Licence

MIT licence.
