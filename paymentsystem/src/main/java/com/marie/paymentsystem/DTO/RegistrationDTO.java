    package com.marie.paymentsystem.DTO;

    import javax.validation.constraints.Email;
    import javax.validation.constraints.NotEmpty;
    import javax.validation.constraints.Size;

    public class RegistrationDTO {
        //TODO ანოტაციები გამოსაკვლევი, notempty ვაძლევს საშუალებას რომ საიზი გამოვიყენოთ

        @NotEmpty(message = "First Name is required")
        private String name;

        @NotEmpty(message = "Last Name is required")
        private String lastName;

        @NotEmpty(message = "Email is required")
        @Email(message = "Email is not valid")
        private String email;

        @NotEmpty(message = "Password is required")
        @Size (min = 8, message = "Minimum password length is 8 characters")
        private String password;

        @NotEmpty(message = "Please confirm password")
        private String confirmPassword;




        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getlastName() {
            return lastName;
        }

        public void setlastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }
    }
