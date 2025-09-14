# Welcome to Student Management App

A full-stack web application for managing student records, built with Spring Boot and deployed on AWS. This was my final project for the Level 6 Advanced Software Development course.

**Live Demo:** [https://studentapp.academy/](https://studentapp.academy/)

## Example Screenshots
![Login Page Screenshot](./Student%20Management%20App%20Assets/login-page.png)
![Single Student Manupulation Screenshot](./Student%20Management%20App%20Assets/single-student-search.png)
![Student Info ScreenShot](./Student%20Management%20App%20Assets/student-info.png)
![Class Group Table Format ScreenShot](./Student%20Management%20App%20Assets/table-format.png)

## Features

* **Secure User Authentication:** Users can log in to access the application.
* **CRUD Functionality:** Easily Create, Read, Update, and Delete student records from the database.
* **Dynamic Data Tables:** View all students in a clear, sortable table.
* **Search Functionality:** Quickly find specific students by name or other criteria.

## Tech Stack

* **Backend:** Java, Spring Boot, Spring Security, Maven
* **Frontend:** HTML, CSS, Thymeleaf
* **Database:** MySQL (Hosted on AWS RDS)
* **Deployment:** AWS EC2, Nginx, SSL (Let's Encrypt)
* **Version Control:** Git / GitHub

## Challenges & Lessons Learned

"A key challenge was integrating the Thymeleaf front-end with the Spring Boot back-end, particularly in passing data from HTML forms to my Java controllers. I overcame this by systematically studying Thymeleaf's attribute syntax to understand how it binds data to Java objects. This was a breakthrough, allowing me to master the use of the @ModelAttribute annotation to handle form submissions cleanly and efficiently."

