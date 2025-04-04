# Better Call TA Developer Guide

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
  - [Architecture](#architecture)
  - [UI Component](#ui-component)
  - [Logic Component](#logic-component)
  - [Model Component](#model-component)
  - [Storage Component](#storage-component)
  - [Common Classes](#common-classes)
- [Implementation](#implementation)
- [Documentation, Logging, Testing, Configuration, DevOps](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
  - [Product Scope](#product-scope)
  - [User Stories](#user-stories)
  - [Use Cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

[AddressBook Level-3](https://se-education.org/addressbook-level3), of which we modified our application from.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with one another.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete /id A0111111N`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.



--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of student contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Better Call TA is a contact management platform for teaching assistants to manage the details of students in their class(es) as well as relevant administrative details


### User stories

| Priority  | As a …​                              | I want to …​                                       | So that I can…​                                             |
|-----------|----------------------------------|------------------------------------------------|------------------------------------------------|
| ***       | New user                         | See usage instructions                        | Refer to instructions when I forget how to use the App |
| ***       | User                             | Add a new person                              | Add a new student to the tutorial group       |
| ***       | User                             | Delete a person                              | Remove students no longer taking the course      |
| ***       | User                             | Find a student by name                                | Locate details of a student without going through the entire list |
| **        | User                  | Hide private contact details              | Minimize the chance of someone else seeing them by accident |
| *         | User with many students in the address book | Sort students by name                                | Locate a person easily                    |
| ***       | TA                               | View my student’s contacts                   | Reference them when writing emails              |
| ***       | TA                               | Add my student’s contact                     | Modify the list when a new student joins        |
| ***       | TA                               | Delete my student’s contact                  | Modify the list if they drop the module        |
| **        | TA                               | Edit my student’s contact                    | Change minute details as the course goes on    |
| **        | TA                               | Track the attendance of a student            | Mark them on participation                     |
| **        | TA                               | Filter my student’s contact by name          | Easily find them for emergencies               |
| **        | TA                               | Import student contact lists                 | Avoid manually entering data for large classes |
| **        | TA                               | Archive student contact information          | Keep my active list clean and organized        |
| **        | TA                               | Use keyboard shortcuts for common actions    | Navigate the platform more efficiently         |
| **        | TA                               | Create a “quick view” summary of a student’s key details | Access essential information at a glance  |
| **        | TA                               | Add a student without filling in all the information | Add a student without having all their details |
| *         | TA                               | Clear my student’s contacts                  | Start with a new list after every semester     |
| *         | TA                               | View the general grade of a student          | Filter out those who may need the most help    |
| * (Epic)  | TA                               | Sort my students according to different categories | Track their progress against certain metrics |
| *         | TA                               | Sort students by attendance                  | Track which classes they have attended        |
| *         | TA                               | Categorize my students into their respective classes | Easily find students belonging to a specific class |
| *         | TA                               | Categorize my students into their group teams | Contact the team as a whole                    |
| *         | TA                               | Sort students by grades                      | See who needs more guidance                   |
| *         | TA                               | Check when a student has an appointment with me | Get to our meeting on time                    |
| *         | TA                               | Track each student’s class participation     | Give them the appropriate marks               |
| *         | TA                               | Set preferred communication methods for each student | Contact them in the way they prefer      |
| *         | TA                               | Add detailed notes to student profiles       | Keep track of important details               |
| *         | TA                               | View a calendar of all student-related events | Stay organized and prepared                   |
| *         | TA                               | Track student accommodations                 | Ensure compliance with university policies    |
| *         | TA                               | Generate visual graphs of student engagement trends | Present data clearly during meetings     |
| *         | TA                               | Toggle between light and dark mode          | Use the platform comfortably in different lighting conditions |



### Use cases

(For all use cases below, the **System** is the `BetterCallTA` and the **Actor** is the `user`, unless specified otherwise)

**<u>Use case: List all students</u>**

**MSS**

1. User requests to list all students
2. BetterCallTA displays all students in an ordered list.

    Use case ends.

**Extensions**

* 1a. List is empty

    Use case ends.

<br>

**<u>Use case: Add a student</u>**

**MSS**

1. User requests to add a student
2. BetterCallTA checks validity of provided information
3. BetterCallTA adds the student

    Use case ends.

**Extensions**

* 1a. Not all information is given.

    * 1a1. BetterCallTA sets fields where information is not given to "NA".

      Use case resumes at step 2.

* 2a. Student ID has incorrect format.

    * 2a1. BetterCallTA shows an error message, specifying the correct format.

      Use case ends.

* 2b. Email is not NA and does not have a correct domain format.

    * 2b1. BetterCallTA shows an error message, specifying that correct domain is missing.

      Use case ends.

* 3a. The student cannot be added to the list.

    * 3a1. BetterCallTA shows an error message.

      Use case ends.

<br>

**<u>Use case: Delete a student</u>**

**MSS**

1.  User requests to <u>list students (UC1)</u>
2.  BetterCallTA shows a list of students
3.  User requests to delete a specific student in the list
4.  BetterCallTA deletes the student

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. BetterCallTA shows an error message.

      Use case resumes at step 2.

<br>

**<u>Use case: See usage instructions</u>**

**MSS**

1. User requests to see all usage instructions
2. BetterCallTA displays a link to the User Guide

   Use case ends.

<br>


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.


### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete /id A0237297R`<br>
      Expected: Contact with `Id` of `A0237297R` is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete /id A9999999R`<br>
      Expected: Assume contact with `Id` of `A9999999R` does not exist. No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete 69`,
      Expected: Similar to previous.
