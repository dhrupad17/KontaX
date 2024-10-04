# 📇 KontaX - Your smart solution to organize, connect, and manage contacts with ease.

**KontaX** is a Java Full Stack Web Application designed to help users manage their contacts efficiently. It features secure authentication, contact organization tools, and an intuitive user interface, offering both functionality and ease of use.



## 🚀 Features

### 🔐 User Authentication
- **Sign-up with Email**: Users can create an account by signing up with an email and password. A verification link is sent to the provided email address, and the user must verify their account to gain access to the application. Until verification, the account remains disabled.
- **OAuth Login**: Users can log in via Google or GitHub. When using these OAuth methods, the account is automatically verified, providing a seamless login experience.
- **Spring Security**: All routes in the application are protected. Unauthenticated users cannot access any features or pages without first logging in.

### 🌐 User Interface
- **Home Page**: The landing page for the application, showcasing its purpose and main features, with smooth navigation links.
- **About Page**: A detailed description of the app's background, purpose, and what it offers to users.
- **Services Page**: Provides a breakdown of the services available within the app, giving users an overview of its features.
- **Contact Page**: Allows users or visitors to reach out to the developer for support or inquiries.

### 🌞/🌚 Light & Dark Theme Toggle
- **Toggle between themes**: The entire application supports a light and dark mode, which users can switch between using a toggle button. This setting persists throughout the user session, enhancing user experience based on their preferences.

### 📁 Contact Management
- **Profile Section**: After logging in, users have access to their profile section, where they can view and edit their account details such as name, profile picture, and other personal information.
- **Add Contact**: A comprehensive form to add new contacts. The form includes fields like:
  - Name, email, phone number, address
  - Description, portfolio link, LinkedIn link
  - Contact image (uploaded to **Cloudinary**)
  - Option to mark contacts as favorites
- **Form Validations**: All fields in the form are thoroughly validated on both the client-side (frontend) and server-side (backend). This ensures that data is entered correctly before submission, with meaningful error messages displayed when something is wrong.
- **View, Edit, Delete Contacts**: Users can:
  - **View contacts**: Ajax-powered modal dialogs display contact details without leaving the current page.
  - **Edit contacts**: Update contact information easily through a similar form.
  - **Delete contacts**: Remove unwanted contacts from the database.
- **Pagination and Search**: The contacts list supports pagination to manage large datasets efficiently. Users can search for specific contacts by fields like name, phone number, or email address.
- **Export Contacts**: Users can export their contact list into an Excel file, providing an easy way to back up their data or transfer it elsewhere.

### 📝 Feedback System
- A feedback form, powered by **Google Forms**, allows users to submit feedback, suggestions, or report bugs. This ensures ongoing improvement of the application based on user input.

---

## 🛠️ Technologies

- **Backend**: Spring Boot for creating RESTful APIs, managing the service logic, and handling database operations.
- **Frontend**: Thymeleaf template engine combined with **Tailwind CSS** and **Flowbite** for responsive and dynamic UI design.
- **Authentication**: OAuth2 and Spring Security for managing login and access control.
- **Cloud Storage**: Cloudinary is integrated for hosting and managing images (especially contact images).
- **Database**: MySQL is used to store user, contact, and profile data.

---

### Database Directory Structure 

(updating soon)

### Local Directory Structure

(updating soon)

### Complete Application Overview

(updating soon)


## 📊 Future Plans

1. **Data Analytics Dashboard**  
   - Users will be able to view a data analytics dashboard on their contacts. This dashboard will feature:
     - 📊 **Gender-based statistics**: A breakdown of how many contacts are male, female, or other.
     - 🗺️ **Location-based visualization**: A map or chart showing where contacts are located.
     - 💼 **Career-based statistics**: Contacts categorized by their profession or industry.
   
2. **Chatbot Integration**  
   - A chatbot will be added to assist users with navigating and using the app. The bot will be able to answer questions, guide users through key features, and provide tips on managing their contacts.

3. **Direct Messaging System**  
   - Users will be able to send direct messages to their contacts. This feature will support:
     - 💬 Text-based messaging
     - 🖼️ Media sharing (images, files)
     - Real-time chat interface for quick communication with any contact.

4. **Freemium and Premium Plans**  
   - KontaX will introduce two types of user accounts:
     - **Freemium**: Users can store up to 50 contacts for free.
     - **Premium**: Users can store unlimited contacts and access the messaging feature. The premium tier will require a monthly or yearly subscription.
     - **UI Changes**: The UI will adapt based on the user’s plan. Premium users will see additional features like direct messaging and advanced analytics.
     - **Payment Gateway**: Integrated payment options for purchasing premium plans.

5. **UI Improvements for Premium Features**  
   - The UI will be enhanced to highlight premium features such as unlimited contacts, advanced analytics, and messaging functionality. Clear pricing and benefits will be showcased in the user dashboard.

6. **Deployment on AWS**  
   - The app will be fully deployed on **AWS** (or another cloud provider), making it accessible globally. Additionally, a custom domain name will be set up for branding.

---

## 🔧 Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dhrupad17/KontaX.git

2. **Database Setup**:
   - Create a new MySQL database and configure the connection in the `application.properties` file.

3. **Run the project**:
   - Using your IDE or the command line, run the following command:
     ```bash
     mvn spring-boot:run
     ```
## 🙏 Acknowledgment

I would like to extend my heartfelt thanks to [Durgesh Tiwari](https://www.youtube.com/playlist?list=PL0zysOflRCemNS641tpw66bcaFylyIGsA) for brainstorming the original idea behind building a Smart Contact Manager. While I have added many new features and enhancements to the application, Durgesh's insights laid the foundation for this project. I have many exciting features planned for future updates, and I look forward to building on this strong foundation.


## 🙌 Contributing
We welcome contributions! Feel free to fork the project, make your changes, and submit a pull request with detailed information about your updates.

## 📬 Feedback
If you have any suggestions, find bugs, or have general feedback, please fill out this [Google Form](https://forms.gle/example-form-link).

## 🛡️ License
This project is licensed under the MIT License. You are free to use, modify, and distribute it, as long as the terms of the license are respected.

---

### Made with ❤️ by [Dhrupad Chakraborty](https://github.com/dhrupad17)

