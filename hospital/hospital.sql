/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 12/06/2023 06:25:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointments
-- ----------------------------
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments`  (
  `appointment_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NULL DEFAULT NULL,
  `doctor_id` int NULL DEFAULT NULL,
  `schedule_id` int NULL DEFAULT NULL,
  `appointment_date_time` datetime NULL DEFAULT NULL,
  `appointment_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  INDEX `appointments_doctors_id`(`doctor_id` ASC) USING BTREE,
  INDEX `appointments_patients_id`(`patient_id` ASC) USING BTREE,
  INDEX `appointments_schedules_id`(`schedule_id` ASC) USING BTREE,
  CONSTRAINT `appointments_doctors_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointments_patients_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointments_schedules_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointments
-- ----------------------------
INSERT INTO `appointments` VALUES (16, 4, 2, 4, '2023-06-10 13:07:27', '等待取号');
INSERT INTO `appointments` VALUES (17, 4, 2, 4, '2023-06-10 13:08:53', '等待取号');

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments`  (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_description` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES (10, '45645', '456');
INSERT INTO `departments` VALUES (18, '453', '4');

-- ----------------------------
-- Table structure for doctors
-- ----------------------------
DROP TABLE IF EXISTS `doctors`;
CREATE TABLE `doctors`  (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_id` int NULL DEFAULT NULL,
  `doctor_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`doctor_id`) USING BTREE,
  INDEX `doctor_id`(`doctor_id` ASC) USING BTREE,
  INDEX `doctors_departments_id`(`department_id` ASC) USING BTREE,
  CONSTRAINT `doctors_departments_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctors
-- ----------------------------
INSERT INTO `doctors` VALUES (2, '456', 10, '456', '45');
INSERT INTO `doctors` VALUES (4, '646', 18, '456', '644');
INSERT INTO `doctors` VALUES (5, '456', 10, '456', '456');
INSERT INTO `doctors` VALUES (6, '7575', 18, '757', '5757');

-- ----------------------------
-- Table structure for patients
-- ----------------------------
DROP TABLE IF EXISTS `patients`;
CREATE TABLE `patients`  (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`patient_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patients
-- ----------------------------
INSERT INTO `patients` VALUES (4, '456', '546', '456');
INSERT INTO `patients` VALUES (5, '45', '456', '456');
INSERT INTO `patients` VALUES (11, '546+', '546+546+', '546+');
INSERT INTO `patients` VALUES (12, '546+', '546+546+', '546+');
INSERT INTO `patients` VALUES (13, '546+', '546+546+', '546+');
INSERT INTO `patients` VALUES (14, '546', '546+546+', '546+');
INSERT INTO `patients` VALUES (15, '546', '546+546+', '546+');
INSERT INTO `patients` VALUES (16, '456', '45645', '456');
INSERT INTO `patients` VALUES (17, '456', '456', '456');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'admin', '管理端的账号');
INSERT INTO `roles` VALUES (2, 'user', '用户端的账号');

-- ----------------------------
-- Table structure for schedules
-- ----------------------------
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules`  (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` int NULL DEFAULT NULL,
  `schedule_date` date NULL DEFAULT NULL,
  `schedule_time` time NULL DEFAULT NULL,
  `schedule_end_time` time NULL DEFAULT NULL,
  PRIMARY KEY (`schedule_id`) USING BTREE,
  INDEX `schedule_id`(`schedule_id` ASC) USING BTREE,
  INDEX `schedules_doctors_id`(`doctor_id` ASC) USING BTREE,
  CONSTRAINT `schedules_doctors_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedules
-- ----------------------------
INSERT INTO `schedules` VALUES (1, 2, '2023-06-22', '19:21:13', '21:48:42');
INSERT INTO `schedules` VALUES (4, 2, '2023-06-12', '08:08:07', '08:08:10');
INSERT INTO `schedules` VALUES (5, 2, '2023-06-20', '16:40:22', '16:40:21');
INSERT INTO `schedules` VALUES (6, 2, '2023-06-12', '12:22:22', '12:22:23');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  `patient_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `users_patiens_id`(`patient_id` ASC) USING BTREE,
  INDEX `users_roles_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `users_patiens_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_roles_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', 1, 4);
INSERT INTO `users` VALUES (18, '456456', '456456', 2, 16);
INSERT INTO `users` VALUES (19, '12312', '313123', 1, 11);
INSERT INTO `users` VALUES (20, '123', '123', 1, 11);

SET FOREIGN_KEY_CHECKS = 1;
