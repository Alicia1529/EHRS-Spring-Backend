CREATE TABLE IF NOT EXISTS `User`(
    `user_email` VARCHAR(50) NOT NULL, 
    `password` VARCHAR(255),
    `role` VARCHAR(10),
    PRIMARY KEY (`user_email`)
);

CREATE TABLE IF NOT EXISTS `Patient`(
    `patient_email` VARCHAR(50) NOT NULL, 
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `gender` VARCHAR(10),
    `birthday` DATE NOT NULL,
    `health_information` JSON,
    `state` VARCHAR(20) NOT NULL DEFAULT "normal",
    PRIMARY KEY (`patient_email`),
    FOREIGN KEY (`patient_email`) REFERENCES User(`user_email`)
);

CREATE TABLE IF NOT EXISTS `Hospital`(
    `hospital_id` VARCHAR(50) NOT NULL, 
    `hospital_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`hospital_id`),
    FOREIGN KEY (`hospital_id`) REFERENCES User(`user_email`)
);

CREATE TABLE IF NOT EXISTS `Department`(
    `department_id` INT NOT NULL AUTO_INCREMENT,
    `hospital_id` VARCHAR(50) NOT NULL,
    `department_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`department_id`),
    FOREIGN KEY (`hospital_id`) REFERENCES Hospital(`hospital_id`)
);

CREATE TABLE IF NOT EXISTS `Doctor`(
    `doctor_email` VARCHAR(50) NOT NULL, 
    `department_id` INT NOT NULL,
    `hospital_id` VARCHAR(50) NOT NULL,
    `title` VARCHAR(30),
    `birthday` DATE NOT NULL,
    `gender` VARCHAR(10),
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`doctor_email`),
    FOREIGN KEY (`hospital_id`) REFERENCES Hospital(`hospital_id`),
    FOREIGN KEY (`department_id`) REFERENCES Department(`department_id`),
    FOREIGN KEY (`doctor_email`) REFERENCES User(`user_email`)
);

CREATE TABLE IF NOT EXISTS `Record`(
    `record_id` INT NOT NULL AUTO_INCREMENT, 
    `patient_email` VARCHAR(50) NOT NULL,
    `doctor_email` VARCHAR(50) NOT NULL,
    `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `case_description` JSON,
    PRIMARY KEY (`record_id`),
    FOREIGN KEY (`patient_email`) REFERENCES Patient(`patient_email`),
    FOREIGN KEY (`doctor_email`) REFERENCES Doctor(`doctor_email`)
);

CREATE TABLE IF NOT EXISTS `LabTest`(
    `lab_test_id` INT NOT NULL AUTO_INCREMENT, 
    `patient_email` VARCHAR(50) NOT NULL,
    `doctor_email` VARCHAR(50) NOT NULL,
    `record_id` INT NOT NULL,
    `datetime` DATETIME NOT NULL,
    `lab_test_status` BOOLEAN,
    `lab_test_description` JSON,
    PRIMARY KEY (`lab_test_id`),
    FOREIGN KEY (`patient_email`) REFERENCES Patient(`patient_email`),
    FOREIGN KEY (`doctor_email`) REFERENCES Doctor(`doctor_email`),
    FOREIGN KEY (`record_id`) REFERENCES Record(`record_id`)
);

CREATE TABLE IF NOT EXISTS `LabReport`(
    `lab_report_id` INT NOT NULL AUTO_INCREMENT, 
    `lab_test_id` INT NOT NULL,
    `lab_report_description` JSON,
    PRIMARY KEY (`lab_report_id`),
    FOREIGN KEY (`lab_test_id`) REFERENCES LabTest(`lab_test_id`)
);

CREATE TABLE IF NOT EXISTS `TimeSlot`(
    `time_slot_id` INT NOT NULL AUTO_INCREMENT,
    `doctor_email` VARCHAR(50) NOT NULL,
    `seat` INT NOT NULL,
    `capacity` INT NOT NULL,
    `date` DATE NOT NULL,
    `start_time` TIME(0) NOT NULL,
    PRIMARY KEY (`time_slot_id`),
    FOREIGN KEY (`doctor_email`) REFERENCES Doctor(`doctor_email`)
);

CREATE TABLE IF NOT EXISTS `Appointment`(
    `appointment_id` INT NOT NULL AUTO_INCREMENT,
    `time_slot_id` INT NOT NULL,
    `patient_email` VARCHAR(50) NOT NULL,
    `doctor_email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`appointment_id`),
    FOREIGN KEY (`time_slot_id`) REFERENCES TimeSlot(`time_slot_id`),
    FOREIGN KEY (`patient_email`) REFERENCES Patient(`patient_email`),
    FOREIGN KEY (`doctor_email`) REFERENCES Doctor(`doctor_email`)
);


