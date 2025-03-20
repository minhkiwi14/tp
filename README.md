[![CI Status](https://github.com/AY2425S2-CS2103T-T10-3/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2425S2-CS2103T-T10-3/tp/actions)

# BetterCallTA

## Overview 📜
**BetterCallTA** is a comprehensive teaching assistant contact management system optimized for use with a Command Line Interface (CLI) while providing the convenience of a Graphical User Interface (GUI). Whether you're managing student records or tracking academic progress, BetterCallTA is here to make your life easier! 🎓

### **Student Management**
BetterCallTA introduces an improved student management system with the ability to:
- **Add** new students ➕
- **Edit** student details ✏️
- **Delete** students 🗑️
- **Clear** the entire contact list 

### **Student Fields**
The following fields are available for student records:

| Field       | Purpose                      | Requirements                  |
|------------|-----------------------------|------------------------------|
| `id`      | Unique student ID            | Format: `A000000X`           |
| `name`    | Student's full name          | Alphanumeric characters only |
| `phone`   | Contact number               | Must be a valid 8-digit number |
| `email`   | Email address                 | Must follow standard email format |
| `course`  | Enrolled course               | Alphanumeric characters only |

### **Commands**
#### **Adding a Student**
```
add /name <name> /id <student-id>
```
Example:
```
add /name Jesse Pinkman /id A0237297N
```

For full details:
```
add /name <name> /id <id> /phone <phone> /email <email> /course <course>
```

Example:
```
add /name Heisenberg /id A0237296N /phone 88888888 /email bluestuff@gmail.com /course CS2103T
```

#### **Editing a Student**
Modify student details using:
```
edit /id <student-id-to-be-edited> [/newid <new-id>] [/name <new-name>] [/phone <new-phone>] [/course <new-course>]
```

Example:
```
edit /id A0123456N /newid A0110110N /phone 89926598 /course CS2109S
```


#### **Deleting a Student**
```
delete /id <student-id>
```

Example:
```
delete /id A0237296N
```

#### **Clearing the Contact List**
```
clear
```
This will remove all student records from the system. 🧹

---

## Known Issues 
The following issues will be addressed in future updates:
- Attendance, participation, and grades are not yet recorded. 📊
- Each student can only be attached to a single course. 🎒
- Courses are currently treated as normal strings without additional data structures. 📚

---

## Future Features 🔮
Exciting updates planned for upcoming releases:
- **Attendance tracking, participation, grades, and notes.** 📝
- **Ability to modify course details.** 🎓
- **Option to add student details without requiring all fields.** 📋
- **Graph visualization of student data.** 📈
- **Theme customization (e.g., light mode).** 🌞

---

## System Requirements 💻
### **Minimum Requirements:**
- Java version 17 or higher ☕
- At least 15MB of free disk space 💾
- Compatible with Windows, macOS, and Linux 🖥️

Stay tuned for more updates, and thank you for using **BetterCallTA**! 🙏

---

**BetterCallTA** - Making Teaching Assistance Better, One Command at a Time! 🚀
