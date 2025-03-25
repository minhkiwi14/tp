
# Better Call TA User Guide

**BetterCallTA** is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BetterCallTA can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
   - [Viewing help: `help`](#viewing-help-help)
   - [Adding a person: `add`](#adding-a-person-add)
   - [Listing all persons : `list`](#listing-all-persons--list)
   - [Editing a person : `edit`](#editing-a-person-edit)
   - [Locating persons by name: `find`](#locating-persons-by-name-find)
   - [Deleting a person : `delete`](#deleting-a-person--delete)
   - [Clearing all entries : `clear`](#clearing-all-entries--clear)
   - [Exiting the program : `exit`](#exiting-the-program--exit)
3. [Saving the data](#saving-the-data)
4. [Editing the data file](#editing-the-data-file)
5. [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
6. [FAQ](#faq)
7. [Known issues](#known-issues)
8. [Command summary](#command-summary)
9. [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>

1. Download the latest `.jar` file from [here]([https://github.com/se-edu/addressbook-level3/releases](https://github.com/AY2425S2-CS2103T-T10-3/tp/releases)).

1. Copy the file to the folder you want to use as the _home folder_ for BetterCallTA.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

| Command                                      | Description                                               |
|----------------------------------------------|-----------------------------------------------------------|
| `list`                                       | Lists all contacts.                                       |
| `add /id A3478231B /name John Doe /phone 98765432 /email johnd@example.com` | Adds a contact named `John Doe` to the Address Book.      |
| `delete /id A1234567B`                       | Deletes the contact with ID `A1234567B` in the current list. |
| `clear`                                      | Deletes all contacts.                                     |
| `exit`                                       | Exits the app.                                            |

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Person

![Person descriptor](images/person.png)

<box type="info" seamless>

### **Properties**<br>

#### `ID` - The unique identifier for your student

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Format for ID</b><br>
    <t>Follows format used by NUS for Student ID</t>
    <ol>
      <li>It must start with <code>A, U, HT, or NT</code></li>
      <li>The starting letters must be followed by <u>7 digits</u></li>
      <li>It must end with one of the letters <code>A, B, E, H, J, L, M, N, R, U, W, X or Y</code></li>
    </ol>
</div><br>

Examples:
* A0738475N
* HT0347856U

___
#### `Name` - Your student's name

There can be duplicate student names, as that is something that <b>can</b> happen!

___
#### `Phone` - Your student's phone number

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Format for Phone</b><br>
    <ol>
      <li>Must <u>only contain numbers</u></li>
      <li>Must be at least <u>3 digits long</u></li>
    </ol>
</div><br>

The default Phone for newly created contacts is `00000000`.

Example:
* 6565166666

___
#### `Email` - Your student's email

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Format for Email</b><br>
    <t> Must be of format local-part@domain and follow the following constraints:
    </t>
    <ol>
      <li>The local-part must <u>only contain alphanumeric characters</u>, excluding these special characters</li>
      <ul>
        <li>+</li>
        <li>_</li>
        <li>.</li>
        <li>-</li>
      </ul>
      <li>The local-part must not <u>start or end</u> with any special characters</li>
      <li>The domain name must end with a domain label at least <u>2 characters
      </u> long</li>
      <li>The domain name must have each domain label start and end <u>with alphanumeric characters</u>
      <li>The domain name must have each domain label <u>consist of alphanumeric characters</u>, separated only by hyphens, if any
    </ol>
</div><br>

The default Email for newly created contacts is `ab@u.nus.edu`.

Examples:
* e123456@u.nus.edu
* example32@gmail.com

___
#### `Course` - The course you assist in that the student is taking

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Format for Course</b><br>
    <t>Follows format used by NUS for Courses</t>
    <ol>
      <li>It must start with <u>2 or 3 letters</u></li>
      <li>The starting letters must be followed by <u>4 digits</u></li>
      <li>(Optional) It can end with <u>up to two letters</u></li>
    </ol>
</div><br>

The default Course for newly created contacts is `AAA0000AA`.

Examples:
* CS2103T
* IS1108

___
#### `Attendance` - The attendance status of your student in the most recent Tutorial or Lab

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Valid Attendance statuses</b><br>
    <t>Only the following strings will be valid</t>
    <ul>
      <li>PRESENT</li>
      <li>ABSENT</li>
      <li>EXCUSED</li>
    </ul>
    <t>You can enter the strings in any capitalisation, as long as it matches the word.</t>
</div><br>

The default Attendance status for newly created contacts is `UNMARKED`.

___
#### `Participation` - The participation status of your student in the most recent Tutorial or Lab

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Valid Participation statuses</b><br>
    <t>Only the following strings will be valid</t>
    <ul>
      <li>EXCELLENT</li>
      <li>GOOD</li>
      <li>AVERAGE</li>
      <li>POOR</li>
      <li>NONE</li>
    </ul>
    <t>You can enter the strings in any capitalisation, as long as it matches the word.</t>
</div><br>

The default Participation status for newly created contacts is `UNMARKED`.

___
#### `Grade` - The predicted grade of your student based on their previous graded assignments

*  You can only enter an integer from `0` to `100` (inclusive) for a grade.

___
#### `Notes` - Additional details that you may want to keep about individual students

You can attach any number of notes to each student.

--------------------------------------------------------------------------------------------------------------------

## Features

**Notes about the Command Format**
- **Words in `UPPER_CASE`**:  
  These represent parameters that must be supplied by the user.  
  Example: In `add /id ID /name NAME`, `NAME` is a parameter that can be used as `add /id A0123456N /name John Doe`.

- **Items in square brackets (`[ ]`)**:  
  These are optional parameters.  
  Example: `add /id ID /name NAME [/phone PHONE_NUMBER]` can be used as:  
  `add /id A0123456N /name John Doe /phone 83746574` or as `add /id A0123456N /name John Doe`.

- **Items with `...` after them**:  
  These parameters can be used multiple times, including zero times.  
  Example: `[/note NOTE]...` can be used as:  
  `add /id A0123456N /name John Doe /note Hi`  
  or even as `add /id A0123456N /name John Doe /note Hi /note Bye`.

- **Parameters can appear in any order**:  
  The order of parameters does not matter.  
  Example: Both of the following are valid commands:  
  `add /id A0123456N /name John Doe`  
  and `add /name John Doe /id A0123456N`.

- **Extraneous parameters will be ignored**:  
  For commands that do not take any parameters (such as `help`, `list`, `exit`, `clear`), any extra parameters will be ignored.  
  Example: If the command is `help`, typing `help 123` will be interpreted as `help`.

- **Copy-pasting issues in PDFs**:  
  When copying commands from a PDF version of this document, be cautious of space characters surrounding line-breaks. These may be omitted when pasted into the application, causing errors. Ensure the format remains correct when transferring the command.

## Commands Overview

### Viewing Help: `help`

Shows a message explaining how to access the help page.

**Format**: `help`

![Help Message](images/helpMessage.png)

---

### Adding a Person: `add`

Adds a person to the address book.

**Format**:  
`add /id ID /name NAME [/phone PHONE_NUMBER] [/email EMAIL] [/course COURSE]`

**Examples**:
- `add /id A4235352H /name John Doe /phone 98765432 /email johnd@example.com`
- `add /id A4045018Y /name Betsy Crowe /email betsycrowe@example.com /phone 1234567 /course CS4215`

---

### Listing All Persons: `list`

Shows a list of all persons in the address book.

**Format**:  
`list`

---

### Editing a Person: `edit`

Edits an existing person in the address book.

**Format**:  
`edit /id ID [/newid NEW_ID] [/name NAME] [/phone PHONE_NUMBER] [/email EMAIL] [/course COURSE]`

**Notes**:  
- Edits the person with the specified `ID`.
- Only the specified properties will be updated. Other properties will remain as they were previously.

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Warning</b>
    <ul>
      <li>At least one optional field must be provided</li>
      <li>Not more than one of each optional field should be provided</li>
      <li>The new ID must not already be used by a different person</li>
    </ul>
</div><br>

**Example**:  
`edit /id A0123456N /phone 91234567 /email johndoe@example.com`  
This will update the phone number and email of the person with ID `A0123456N` to `91234567` and `johndoe@example.com` respectively.

---

### Locating Persons by Name: `find`

Finds persons whose names contain any of the given keywords.

**Format**:  
`find KEYWORD [MORE_KEYWORDS]`

<div style="background-color: #98fB98; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Tips</b>
    <ul>
      <li>The search is case-insensitive, e.g <code>hans</code> will match <code>Hans</code></li>
      <li>The order of the keywords does not matter, e.g. <code>Hans Bo</code> will match <code>Bo Hans</code></li>
      <li>Persons matching at least one keyword will be returned (i.e. <code>OR</code> search),<br>
      e.g. <code>Hans Bo</code> will return <code>Hans Gruber</code> , <code>Bo Yang</code></li>
    </ul>
</div><br>

<div style="background-color: #fde68a; padding: 10px; border: 1px solid #000; border-radius: 5px; color: #000">
    <b>Warning</b>
    <ul>
      <li>Only the name is searched</li>
      <li>Only full words will be matched e.g. <code>Han</code> will not match <code>Hans</code></li>
    </ul>
</div><br>

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

<br>

  ![result for 'find alex david'](images/findAlexDavidResult.png)

---

### Deleting a Person: `delete`

Deletes the specified person from the address book.

**Format**:  
`delete /id ID`

* Deletes the person with the specified `ID`.
* The ID refers to the ID belonging to the person in the person list.

**Examples**:
- `delete /id A1234567N` deletes the person with ID `A1234567N`.
- `delete /id A7654321B` deletes the person with ID `A7654321B`.

---

### Clearing All Entries: `clear`

Clears all entries from the address book.

**Format**:  
`clear`

---

### Exiting the Program: `exit`

Exits the program.

**Format**:  
`exit`

---

### Saving the Data

BetterCallTA data is automatically saved to the hard disk after any command that changes the data. There is **no need** to save manually.

---

### Editing the Data File

BetterCallTA data is saved automatically as a JSON file located at:  
`[JAR file location]/data/addressbook.json`

**Warning for Advanced Users**:  
You can update the data directly by editing the file, but be cautious.

#### **Caution**:  
- If your changes make the file's format invalid, BetterCallTA will discard all data and start with an empty file the next time it is run.
- **Backup** the data file before editing it to prevent data loss.
- Incorrect edits could cause unexpected behavior (e.g., invalid values or

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BetterCallTA home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add /id ID /name NAME [/phone PHONE_NUMBER] [/email EMAIL] [/course COURSE]…​` <br> e.g., `add /id A0123456E /name James Ho /phone 22224444 /email jamesho@example.com`
**Clear**  | `clear`
**Delete** | `delete /id ID`<br> e.g., `delete /id A3456712E`
**Edit**   | `edit /id ID [/newid NEW_ID] [/name NAME] [/phone PHONE_NUMBER] [/email EMAIL] [/course COURSE]…​`<br> e.g.,`edit /id A0123456N /phone 91234567 /email johndoe@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`

--------------------------------------------------------------------------------------------------------------------

## Glossary

`TA` - Teaching Assistant<br>
Assists the instructor in teaching a course.
