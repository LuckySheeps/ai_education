/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : ai_education

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/03/2025 15:49:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `discussion_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `user_type` int NULL DEFAULT NULL,
  `comment_title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comment_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comment_time` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (24, 7, 11, 1, '1', '1', '2024-12-22');
INSERT INTO `comment` VALUES (25, 8, 16, 0, 'javaweb应该如何学习', '  要头发伤钱，要钱伤头发。如果说你已经下定了决心，为了钱不惜一切代价，请往后看。\n\n        从我讲课以来，有很多朋友问过我，java web应该如何学。\n\n        的确，java后端技术知识体系非常庞大，很多初学者往往一头雾水，不知道怎么学，学些什么东西。先学什么，后学什么等等。总之，没有一个系统而完整的学习路线。\n\n        个人认为，对于java后端的技术，在工作后，如果不是刻意，有计划并且能够持之以恒的去积累的话，确实是难以全面掌握，从而成为一个资深的开发人员。\n\n        如果你认为自己是一个能吃苦耐劳，只是缺少一个合理的学习进阶路线的话。不妨参考下本文的规划。按照本文规划的顺序去一点点夯实自己的知识体系。\n\n        当然，本文规划的内容，相信各位朋友在自己的工作中，或多或少也掌握了一部分。还有一部分朋友可能会觉得，我只需要掌握其中一部分内容就可以顺利上手工作了，没那么麻烦。这点我也不否认，不过我认为，对于这些内容你自然是掌握得越多越全，你的竞争力就会越大。\n\n        需要怎么学，学哪一部分，以及学到什么程度。大家根据各自的情况见仁见智吧。在这里，我只是给一个进阶路线图，供大家参考。也算是给信任我，曾经咨询过我，以及未来可能还要咨询我这个问题的朋友一个交代吧。\n\n        当然，我自己也强烈认同这个学习路线。并且打算在未来的日子里，努力让自己也按这份进阶路线图，重新查漏补缺，回炉。这个进阶路线就', '2024-12-22');
INSERT INTO `comment` VALUES (26, 9, 16, 0, '程序设计', '程序是怎样设计的', '2024-12-28');
INSERT INTO `comment` VALUES (27, 7, 16, 0, '软件设计', '有什么软件设计的模型', '2024-12-28');
INSERT INTO `comment` VALUES (28, 10, 16, 0, '1', '1', '2024-12-29');
INSERT INTO `comment` VALUES (29, 10, 16, 0, '1', '11', '2024-12-29');
INSERT INTO `comment` VALUES (30, 8, 16, 0, '好好学习', '天天向上', '2024-12-30');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `invitation_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (18, '11', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/63657d41-7ed7-42c2-bbaf-5be3545fc31c.png', '11', '66', '5ad2b60a', '111', '11', '11');
INSERT INTO `course` VALUES (19, 'javaweb', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/d00cb2d5-f6cc-4c20-807c-8a0d6c1995fa.png', '10', '肖安迪', '3e778244', 'java', '6', '6666');
INSERT INTO `course` VALUES (20, '世况认化身个', 'http://dummyimage.com/400x400', '12', 'zq', 'cfa6db0e', NULL, NULL, NULL);
INSERT INTO `course` VALUES (21, '软件结构设计', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/d00cb2d5-f6cc-4c20-807c-8a0d6c1995fa.png', '10', '肖安迪', 'b41e5b6c', '学习软件的概要设计', '严格考勤', '好好学习');
INSERT INTO `course` VALUES (22, '英语', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/d00cb2d5-f6cc-4c20-807c-8a0d6c1995fa.png', '10', '肖安迪', '94c6c348', '英语学习', '好好学习', '天天');
INSERT INTO `course` VALUES (25, '高数', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/d8a264d0-c8b1-48af-948e-b2ca6bcb9823.png', '10', '肖安迪', '40394f8a', '高等数学', '好好学习', '天天向上');
INSERT INTO `course` VALUES (26, '计算机网络', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/3e2432ae-44c1-4c29-befa-3574e61b5ed3.png', '10', '肖安迪', 'a5ae96ae', '网络', '好好学习', '天天向上');
INSERT INTO `course` VALUES (27, 'python', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/d00cb2d5-f6cc-4c20-807c-8a0d6c1995fa.png', '10', '肖安迪', 'dd93f236', 'python学习', '好好学习', '天天向上');

-- ----------------------------
-- Table structure for discussions
-- ----------------------------
DROP TABLE IF EXISTS `discussions`;
CREATE TABLE `discussions`  (
  `discussion_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`discussion_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussions
-- ----------------------------
INSERT INTO `discussions` VALUES (7, 18);
INSERT INTO `discussions` VALUES (8, 19);
INSERT INTO `discussions` VALUES (9, 21);
INSERT INTO `discussions` VALUES (10, 20);

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `material_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `material_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  `teacher_id` int NULL DEFAULT NULL,
  `create_date` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`material_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (14, 'e46f1d9f-abd3-490b-865c-73af31e2b545.docx', '13.4 KB', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/93eca4f7-1c31-4334-9ab2-b4637848d193.docx', 19, 10, '2024-12-28');
INSERT INTO `material` VALUES (15, 'e46f1d9f-abd3-490b-865c-73af31e2b545.docx', '13.4 KB', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/182ad0d3-0648-4be4-a82c-e3f9828e506d.docx', 19, 10, '2024-12-29');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `sc_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`sc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES (29, 16, 18);
INSERT INTO `sc` VALUES (30, 16, 19);
INSERT INTO `sc` VALUES (32, 16, 22);
INSERT INTO `sc` VALUES (33, 16, 21);
INSERT INTO `sc` VALUES (36, 16, 25);
INSERT INTO `sc` VALUES (38, 16, 20);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identity` int NULL DEFAULT NULL,
  `student_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `educational_background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (16, '123456', '12', 1, '123456', 0, 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/10089b46-dab0-4793-9453-4a7096598681.jpg', '1', '湖南省张家界市永定区子午西路108号', '111111', '11111');

-- ----------------------------
-- Table structure for student_course_tasks
-- ----------------------------
DROP TABLE IF EXISTS `student_course_tasks`;
CREATE TABLE `student_course_tasks`  (
  `student_course_task_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL,
  `task_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  `status` int UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`student_course_task_id`) USING BTREE,
  INDEX `taskId`(`task_id`) USING BTREE,
  CONSTRAINT `taskId` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_course_tasks
-- ----------------------------
INSERT INTO `student_course_tasks` VALUES (81, 16, 51, 22, 1);
INSERT INTO `student_course_tasks` VALUES (88, 16, 58, 19, 1);
INSERT INTO `student_course_tasks` VALUES (89, 16, 59, 19, 1);
INSERT INTO `student_course_tasks` VALUES (90, 16, 60, 19, 0);
INSERT INTO `student_course_tasks` VALUES (91, 16, 61, 19, 1);

-- ----------------------------
-- Table structure for study_records
-- ----------------------------
DROP TABLE IF EXISTS `study_records`;
CREATE TABLE `study_records`  (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 451 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of study_records
-- ----------------------------
INSERT INTO `study_records` VALUES (305, 11, 18, '2024-12-21 22:11:30');
INSERT INTO `study_records` VALUES (306, 11, 18, '2024-12-21 22:12:20');
INSERT INTO `study_records` VALUES (307, 10, 19, '2024-12-21 22:13:46');
INSERT INTO `study_records` VALUES (308, 11, 18, '2024-12-21 22:15:51');
INSERT INTO `study_records` VALUES (309, 11, 18, '2024-12-21 22:16:12');
INSERT INTO `study_records` VALUES (310, 10, 19, '2024-12-21 22:18:10');
INSERT INTO `study_records` VALUES (311, 16, 19, '2024-12-21 22:18:22');
INSERT INTO `study_records` VALUES (312, 16, 20, '2024-12-21 23:00:57');
INSERT INTO `study_records` VALUES (313, 16, 19, '2024-12-21 23:06:38');
INSERT INTO `study_records` VALUES (314, 10, 19, '2024-12-22 00:12:48');
INSERT INTO `study_records` VALUES (315, 11, 18, '2024-12-22 08:52:49');
INSERT INTO `study_records` VALUES (316, 11, 18, '2024-12-22 08:59:54');
INSERT INTO `study_records` VALUES (317, 10, 19, '2024-12-22 09:06:42');
INSERT INTO `study_records` VALUES (318, 11, 18, '2024-12-22 09:20:51');
INSERT INTO `study_records` VALUES (319, 11, 18, '2024-12-22 09:21:09');
INSERT INTO `study_records` VALUES (320, 11, 18, '2024-12-22 09:21:41');
INSERT INTO `study_records` VALUES (321, 11, 18, '2024-12-22 09:21:44');
INSERT INTO `study_records` VALUES (322, 11, 18, '2024-12-22 09:21:52');
INSERT INTO `study_records` VALUES (323, 10, 19, '2024-12-22 09:24:14');
INSERT INTO `study_records` VALUES (324, 16, 19, '2024-12-22 09:24:45');
INSERT INTO `study_records` VALUES (325, 10, 19, '2024-12-22 09:24:48');
INSERT INTO `study_records` VALUES (326, 16, 19, '2024-12-22 11:24:20');
INSERT INTO `study_records` VALUES (327, 10, 19, '2024-12-22 11:27:29');
INSERT INTO `study_records` VALUES (328, 10, 19, '2024-12-22 14:33:49');
INSERT INTO `study_records` VALUES (329, 10, 19, '2024-12-22 14:34:23');
INSERT INTO `study_records` VALUES (330, 10, 19, '2024-12-22 14:46:44');
INSERT INTO `study_records` VALUES (331, 16, 19, '2024-12-22 14:47:00');
INSERT INTO `study_records` VALUES (332, 10, 19, '2024-12-22 15:55:42');
INSERT INTO `study_records` VALUES (333, 16, 19, '2024-12-22 15:59:13');
INSERT INTO `study_records` VALUES (334, 16, 19, '2024-12-22 16:03:07');
INSERT INTO `study_records` VALUES (335, 10, 21, '2024-12-22 16:03:25');
INSERT INTO `study_records` VALUES (336, 10, 19, '2024-12-22 16:03:27');
INSERT INTO `study_records` VALUES (337, 10, 21, '2024-12-22 16:03:45');
INSERT INTO `study_records` VALUES (338, 10, 21, '2024-12-22 16:04:00');
INSERT INTO `study_records` VALUES (339, 10, 19, '2024-12-22 16:04:22');
INSERT INTO `study_records` VALUES (340, 10, 21, '2024-12-22 16:04:22');
INSERT INTO `study_records` VALUES (341, 10, 19, '2024-12-22 16:04:22');
INSERT INTO `study_records` VALUES (342, 10, 19, '2024-12-22 16:04:22');
INSERT INTO `study_records` VALUES (343, 10, 19, '2024-12-22 16:04:26');
INSERT INTO `study_records` VALUES (344, 16, 19, '2024-12-22 16:04:55');
INSERT INTO `study_records` VALUES (345, 16, 19, '2024-12-22 16:05:03');
INSERT INTO `study_records` VALUES (346, 10, 21, '2024-12-22 16:05:08');
INSERT INTO `study_records` VALUES (347, 16, 19, '2024-12-22 16:05:19');
INSERT INTO `study_records` VALUES (348, 10, 19, '2024-12-22 16:05:21');
INSERT INTO `study_records` VALUES (349, 16, 19, '2024-12-22 16:05:24');
INSERT INTO `study_records` VALUES (350, 16, 19, '2024-12-22 16:07:01');
INSERT INTO `study_records` VALUES (351, 16, 19, '2024-12-22 16:09:17');
INSERT INTO `study_records` VALUES (352, 16, 19, '2024-12-22 16:09:54');
INSERT INTO `study_records` VALUES (353, 16, 21, '2024-12-22 16:10:08');
INSERT INTO `study_records` VALUES (354, 16, 21, '2024-12-22 16:10:10');
INSERT INTO `study_records` VALUES (355, 16, 20, '2024-12-22 16:10:16');
INSERT INTO `study_records` VALUES (356, 16, 19, '2024-12-22 16:11:19');
INSERT INTO `study_records` VALUES (357, 10, 21, '2024-12-22 16:12:35');
INSERT INTO `study_records` VALUES (358, 16, 20, '2024-12-22 16:16:18');
INSERT INTO `study_records` VALUES (359, 10, 19, '2024-12-22 16:17:55');
INSERT INTO `study_records` VALUES (360, 10, 21, '2024-12-22 16:25:35');
INSERT INTO `study_records` VALUES (361, 10, 19, '2024-12-22 16:25:39');
INSERT INTO `study_records` VALUES (362, 10, 21, '2024-12-22 17:03:34');
INSERT INTO `study_records` VALUES (363, 10, 19, '2024-12-22 17:10:57');
INSERT INTO `study_records` VALUES (364, 10, 21, '2024-12-22 17:11:20');
INSERT INTO `study_records` VALUES (365, 10, 21, '2024-12-22 17:11:46');
INSERT INTO `study_records` VALUES (366, 10, 19, '2024-12-22 17:51:12');
INSERT INTO `study_records` VALUES (367, 10, 19, '2024-12-22 17:51:54');
INSERT INTO `study_records` VALUES (368, 10, 19, '2024-12-22 17:52:05');
INSERT INTO `study_records` VALUES (369, 10, 19, '2024-12-22 17:52:23');
INSERT INTO `study_records` VALUES (370, 16, 20, '2024-12-23 08:59:02');
INSERT INTO `study_records` VALUES (371, 16, 18, '2024-12-23 08:59:08');
INSERT INTO `study_records` VALUES (372, 10, 19, '2024-12-23 09:00:36');
INSERT INTO `study_records` VALUES (373, 10, 22, '2024-12-28 08:21:10');
INSERT INTO `study_records` VALUES (374, 10, 22, '2024-12-28 08:21:42');
INSERT INTO `study_records` VALUES (375, 10, 22, '2024-12-28 08:22:00');
INSERT INTO `study_records` VALUES (376, 10, 22, '2024-12-28 08:22:37');
INSERT INTO `study_records` VALUES (377, 10, 22, '2024-12-28 08:23:20');
INSERT INTO `study_records` VALUES (378, 10, 22, '2024-12-28 08:24:56');
INSERT INTO `study_records` VALUES (379, 10, 22, '2024-12-28 08:25:24');
INSERT INTO `study_records` VALUES (380, 10, 22, '2024-12-28 08:25:32');
INSERT INTO `study_records` VALUES (381, 16, 22, '2024-12-28 08:26:23');
INSERT INTO `study_records` VALUES (382, 16, 22, '2024-12-28 09:06:53');
INSERT INTO `study_records` VALUES (383, 16, 22, '2024-12-28 09:39:31');
INSERT INTO `study_records` VALUES (384, 10, 22, '2024-12-28 09:39:45');
INSERT INTO `study_records` VALUES (385, 16, 22, '2024-12-28 09:44:46');
INSERT INTO `study_records` VALUES (386, 10, 22, '2024-12-28 10:26:34');
INSERT INTO `study_records` VALUES (387, 10, 22, '2024-12-28 10:26:36');
INSERT INTO `study_records` VALUES (388, 16, 21, '2024-12-28 19:21:00');
INSERT INTO `study_records` VALUES (389, 16, 18, '2024-12-28 19:29:42');
INSERT INTO `study_records` VALUES (390, 10, 19, '2024-12-28 19:39:48');
INSERT INTO `study_records` VALUES (391, 16, 18, '2024-12-28 19:40:08');
INSERT INTO `study_records` VALUES (392, 16, 20, '2024-12-28 19:40:18');
INSERT INTO `study_records` VALUES (393, 10, 21, '2024-12-28 19:43:07');
INSERT INTO `study_records` VALUES (394, 10, 19, '2024-12-28 19:43:08');
INSERT INTO `study_records` VALUES (395, 16, 21, '2024-12-28 19:43:38');
INSERT INTO `study_records` VALUES (396, 16, 20, '2024-12-28 19:43:49');
INSERT INTO `study_records` VALUES (397, 10, 21, '2024-12-28 19:44:52');
INSERT INTO `study_records` VALUES (398, 16, 21, '2024-12-28 19:46:03');
INSERT INTO `study_records` VALUES (399, 10, 23, '2024-12-28 20:39:41');
INSERT INTO `study_records` VALUES (400, 10, 23, '2024-12-28 20:40:11');
INSERT INTO `study_records` VALUES (401, 10, 24, '2024-12-28 20:52:54');
INSERT INTO `study_records` VALUES (402, 10, 24, '2024-12-28 20:53:32');
INSERT INTO `study_records` VALUES (403, 10, 24, '2024-12-28 20:55:19');
INSERT INTO `study_records` VALUES (404, 10, 24, '2024-12-28 20:55:23');
INSERT INTO `study_records` VALUES (405, 10, 24, '2024-12-28 20:55:24');
INSERT INTO `study_records` VALUES (406, 10, 24, '2024-12-28 20:55:25');
INSERT INTO `study_records` VALUES (407, 10, 19, '2024-12-28 20:55:32');
INSERT INTO `study_records` VALUES (408, 10, 19, '2024-12-28 20:56:53');
INSERT INTO `study_records` VALUES (409, 10, 19, '2024-12-28 21:19:42');
INSERT INTO `study_records` VALUES (410, 10, 19, '2024-12-28 21:21:50');
INSERT INTO `study_records` VALUES (411, 16, 24, '2024-12-29 10:26:24');
INSERT INTO `study_records` VALUES (412, 16, 24, '2024-12-29 10:26:28');
INSERT INTO `study_records` VALUES (413, 16, 24, '2024-12-29 10:27:13');
INSERT INTO `study_records` VALUES (414, 16, 20, '2024-12-29 10:28:46');
INSERT INTO `study_records` VALUES (415, 16, 18, '2024-12-29 10:40:47');
INSERT INTO `study_records` VALUES (416, 16, 18, '2024-12-29 10:52:09');
INSERT INTO `study_records` VALUES (417, 16, 20, '2024-12-29 10:58:12');
INSERT INTO `study_records` VALUES (418, 16, 21, '2024-12-29 11:01:18');
INSERT INTO `study_records` VALUES (419, 16, 21, '2024-12-29 11:01:33');
INSERT INTO `study_records` VALUES (420, 16, 18, '2024-12-29 11:12:15');
INSERT INTO `study_records` VALUES (421, 16, 20, '2024-12-29 11:15:13');
INSERT INTO `study_records` VALUES (422, 16, 18, '2024-12-29 14:32:34');
INSERT INTO `study_records` VALUES (423, 10, 19, '2024-12-29 14:35:42');
INSERT INTO `study_records` VALUES (424, 16, 19, '2024-12-29 14:37:05');
INSERT INTO `study_records` VALUES (425, 16, 18, '2024-12-29 14:39:54');
INSERT INTO `study_records` VALUES (426, 16, 18, '2024-12-29 14:40:41');
INSERT INTO `study_records` VALUES (427, 10, 25, '2024-12-29 14:42:23');
INSERT INTO `study_records` VALUES (428, 16, 20, '2024-12-29 14:43:31');
INSERT INTO `study_records` VALUES (429, 16, 20, '2024-12-29 14:43:49');
INSERT INTO `study_records` VALUES (430, 16, 20, '2024-12-29 14:44:22');
INSERT INTO `study_records` VALUES (431, 16, 18, '2024-12-30 08:19:47');
INSERT INTO `study_records` VALUES (432, 16, 18, '2024-12-30 08:20:42');
INSERT INTO `study_records` VALUES (433, 16, 18, '2024-12-30 08:21:33');
INSERT INTO `study_records` VALUES (434, 10, 19, '2024-12-30 08:22:23');
INSERT INTO `study_records` VALUES (435, 16, 19, '2024-12-30 08:23:47');
INSERT INTO `study_records` VALUES (436, 10, 22, '2024-12-30 08:40:42');
INSERT INTO `study_records` VALUES (437, 10, 21, '2024-12-30 08:47:00');
INSERT INTO `study_records` VALUES (438, 10, 22, '2024-12-30 08:47:21');
INSERT INTO `study_records` VALUES (439, 10, 19, '2024-12-30 08:48:37');
INSERT INTO `study_records` VALUES (440, 16, 20, '2024-12-30 09:01:37');
INSERT INTO `study_records` VALUES (441, 16, 18, '2024-12-30 09:17:32');
INSERT INTO `study_records` VALUES (442, 10, 22, '2024-12-30 09:20:07');
INSERT INTO `study_records` VALUES (443, 16, 19, '2024-12-30 09:32:25');
INSERT INTO `study_records` VALUES (444, 10, 19, '2024-12-30 09:37:21');
INSERT INTO `study_records` VALUES (445, 16, 18, '2024-12-30 09:40:59');
INSERT INTO `study_records` VALUES (446, 10, 19, '2024-12-30 09:42:28');
INSERT INTO `study_records` VALUES (447, 16, 18, '2024-12-30 09:43:05');
INSERT INTO `study_records` VALUES (448, 16, 20, '2024-12-30 09:43:12');
INSERT INTO `study_records` VALUES (449, 16, 20, '2024-12-30 09:43:22');
INSERT INTO `study_records` VALUES (450, 16, 19, '2024-12-30 09:43:36');
INSERT INTO `study_records` VALUES (451, 10, 19, '2024-12-30 09:44:35');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NULL DEFAULT NULL,
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deadline` date NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (34, 20, '即该得细影', 'esse', '2024-12-22');
INSERT INTO `task` VALUES (51, 22, '测试四', '', '2025-01-13');
INSERT INTO `task` VALUES (58, 19, '测试1', '', '2025-01-03');
INSERT INTO `task` VALUES (59, 19, '测试2', '', '2025-01-11');
INSERT INTO `task` VALUES (60, 19, '测试4', '', '2025-01-15');
INSERT INTO `task` VALUES (61, 19, 'web测试', '', '2024-12-30');

-- ----------------------------
-- Table structure for task_records
-- ----------------------------
DROP TABLE IF EXISTS `task_records`;
CREATE TABLE `task_records`  (
  `topic_id` int NOT NULL,
  `task_id` int NOT NULL,
  `student_id` int NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `right_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ai_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `get_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `question_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_records
-- ----------------------------
INSERT INTO `task_records` VALUES (73, 32, 16, '2', 'B', '0', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (74, 33, 16, '2', '2', '1', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (75, 34, 16, '4', '2', '0', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (76, 35, 16, '2', '2', '1', NULL, '50', '选择题');
INSERT INTO `task_records` VALUES (77, 35, 16, '正确', '正确', '1', NULL, '50', '判断题');
INSERT INTO `task_records` VALUES (78, 36, 16, '2', '2', '1', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (79, 36, 16, '错误', '正确', '0', NULL, '0', '判断题');
INSERT INTO `task_records` VALUES (80, 38, 16, '1', '1', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (81, 38, 16, '2', '2', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (82, 38, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (83, 38, 16, '错误', '错误', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (84, 39, 16, '2', '2', NULL, NULL, '100', '应用题');
INSERT INTO `task_records` VALUES (85, 40, 16, '1', '1', '1', NULL, '100', '选择题');
INSERT INTO `task_records` VALUES (86, 40, 16, '正确', '正确', '1', NULL, '1111', '判断题');
INSERT INTO `task_records` VALUES (87, 40, 16, '111', '11', NULL, NULL, '0', '简答题');
INSERT INTO `task_records` VALUES (88, 41, 16, '1', '1', '1', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (89, 41, 16, '1', '2', '0', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (90, 41, 16, '正确', '正确', '1', NULL, '0', '判断题');
INSERT INTO `task_records` VALUES (91, 41, 16, '正确', '错误', '0', NULL, '0', '判断题');
INSERT INTO `task_records` VALUES (92, 41, 16, '1', '1', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (93, 41, 16, '2', '2', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (94, 42, 16, '1', 'A', '0', NULL, '30', '选择题');
INSERT INTO `task_records` VALUES (95, 42, 16, '正确', '正确', '1', NULL, '30', '判断题');
INSERT INTO `task_records` VALUES (96, 42, 16, '11', '213312', NULL, NULL, '30', '应用题');
INSERT INTO `task_records` VALUES (97, 43, 16, '1', '1', '1', NULL, '10', '选择题');
INSERT INTO `task_records` VALUES (98, 43, 16, '2', '2', '1', NULL, '10', '选择题');
INSERT INTO `task_records` VALUES (99, 43, 16, '正确', '正确', '1', NULL, '10', '判断题');
INSERT INTO `task_records` VALUES (100, 43, 16, '正确', '错误', '0', NULL, '0', '判断题');
INSERT INTO `task_records` VALUES (101, 43, 16, '1', '1', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (102, 43, 16, '2', '2', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (103, 44, 16, '1', '1', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (104, 45, 16, '1', '1', NULL, NULL, '0', '应用题');
INSERT INTO `task_records` VALUES (105, 46, 16, '1', '1', NULL, NULL, '66', '应用题');
INSERT INTO `task_records` VALUES (106, 47, 16, '1', '1', '1', NULL, '40', '选择题');
INSERT INTO `task_records` VALUES (107, 47, 16, '正确', '正确', '1', NULL, '40', '判断题');
INSERT INTO `task_records` VALUES (108, 47, 16, '1', '1', NULL, NULL, '10', '应用题');
INSERT INTO `task_records` VALUES (109, 48, 16, '1', '1', '1', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (110, 48, 16, '正确', '错误', '0', NULL, '0', '判断题');
INSERT INTO `task_records` VALUES (111, 48, 16, '2', '1', NULL, NULL, '0', '简答题');
INSERT INTO `task_records` VALUES (112, 49, 16, '2', '2', NULL, NULL, '0', '简答题');
INSERT INTO `task_records` VALUES (113, 50, 16, '1', '1', '1', NULL, '50', '选择题');
INSERT INTO `task_records` VALUES (114, 50, 16, '2', '2', NULL, NULL, '0', '简答题');
INSERT INTO `task_records` VALUES (115, 51, 16, '1', '1', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (116, 51, 16, '错误', '错误', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (117, 51, 16, '2', '1', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (118, 52, 16, '2', 'B', '0', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (119, 52, 16, '2', 'A', '0', NULL, '0', '选择题');
INSERT INTO `task_records` VALUES (120, 52, 16, '正确', '正确', '1', NULL, '5', '判断题');
INSERT INTO `task_records` VALUES (121, 52, 16, '错误', '错误', '1', NULL, '5', '判断题');
INSERT INTO `task_records` VALUES (122, 52, 16, '111', '111', NULL, NULL, '0', '简答题');
INSERT INTO `task_records` VALUES (123, 53, 16, '2', '2', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (124, 53, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (125, 53, 16, '6', '6', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (126, 54, 16, '1', '1', '1', NULL, '100', '选择题');
INSERT INTO `task_records` VALUES (127, 55, 16, '1', '1', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (128, 56, 16, '2', '2', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (129, 56, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (130, 56, 16, '2', '2', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (131, 57, 16, '1', '1', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (132, 57, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (133, 57, 16, '1', '1', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (134, 58, 16, '2', '2', '1', NULL, '50', '选择题');
INSERT INTO `task_records` VALUES (135, 58, 16, '4', '4', '1', NULL, '50', '选择题');
INSERT INTO `task_records` VALUES (136, 59, 16, '4', '4', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (137, 59, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (138, 59, 16, '2', '2', NULL, NULL, '50', '应用题');
INSERT INTO `task_records` VALUES (142, 61, 16, '2', '2', '1', NULL, '25', '选择题');
INSERT INTO `task_records` VALUES (143, 61, 16, '正确', '正确', '1', NULL, '25', '判断题');
INSERT INTO `task_records` VALUES (144, 61, 16, '2', '2', NULL, NULL, '50', '应用题');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identity` int NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `introduction` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (10, '肖安迪', 'javaweb', '123456', 1, '123456', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/b4ae186f-3c87-4b16-b360-426feedfdcbe.jpg', 0, '一名好老师\n');
INSERT INTO `teacher` VALUES (11, '66', '66', '11', 1, '11', 'https://new-aieducation.oss-cn-beijing.aliyuncs.com/b4ae186f-3c87-4b16-b360-426feedfdcbe.jpg', 0, '6666');
INSERT INTO `teacher` VALUES (12, 'zq', '77', '1234567', 1, '1234567', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `task_id` bigint NULL DEFAULT NULL,
  `question_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `question_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `options1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `options2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `options3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `options4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `correct_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fullscore` int NULL DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (32, '选择题', '1+1=', '1', '2', '3', '4', 'B', '2024-12-21 22:19:39', 100, 73);
INSERT INTO `topic` VALUES (33, '选择题', '1+1', '1', '2', '3', '4', '2', '2024-12-21 22:22:39', 100, 74);
INSERT INTO `topic` VALUES (34, '选择题', '1+1', '1', '2', '3', '4', '2', '2024-12-21 00:00:00', 100, 75);
INSERT INTO `topic` VALUES (35, '选择题', '1+1', '1', '2', '3', '4', '2', '2024-12-21 23:06:28', 50, 76);
INSERT INTO `topic` VALUES (35, '判断题', '1+1=2', '正确', '错误', NULL, NULL, '正确', '2024-12-21 23:06:28', 50, 77);
INSERT INTO `topic` VALUES (36, '选择题', '1+1', '1', '2', '3', '4', '2', '2024-12-21 23:12:40', 50, 78);
INSERT INTO `topic` VALUES (36, '判断题', '1+1=2', '正确', '错误', NULL, NULL, '正确', '2024-12-21 23:12:40', 50, 79);
INSERT INTO `topic` VALUES (38, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-22 00:14:12', 25, 80);
INSERT INTO `topic` VALUES (38, '选择题', '2', '1', '2', '3', '4', '2', '2024-12-22 00:14:12', 25, 81);
INSERT INTO `topic` VALUES (38, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-22 00:14:12', 25, 82);
INSERT INTO `topic` VALUES (38, '判断题', '2', '正确', '错误', NULL, NULL, '错误', '2024-12-22 00:14:12', 25, 83);
INSERT INTO `topic` VALUES (39, '应用题', '1+1', NULL, NULL, NULL, NULL, '2', '2024-12-22 00:15:47', 100, 84);
INSERT INTO `topic` VALUES (40, '选择题', '·111', '11', '1', '1', '1', '1', '2024-12-22 09:22:27', 100, 85);
INSERT INTO `topic` VALUES (40, '判断题', '11', '正确', '错误', NULL, NULL, '正确', '2024-12-22 09:22:27', 1111, 86);
INSERT INTO `topic` VALUES (40, '应用题', '111', NULL, NULL, NULL, NULL, '11', '2024-12-22 09:22:27', 111, 87);
INSERT INTO `topic` VALUES (41, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-22 15:58:38', 10, 88);
INSERT INTO `topic` VALUES (41, '选择题', '2', '1', '2', '3', '4', '2', '2024-12-22 15:58:38', 10, 89);
INSERT INTO `topic` VALUES (41, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-22 15:58:38', 10, 90);
INSERT INTO `topic` VALUES (41, '判断题', '2', '正确', '错误', NULL, NULL, '错误', '2024-12-22 15:58:38', 10, 91);
INSERT INTO `topic` VALUES (41, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 15:58:38', 30, 92);
INSERT INTO `topic` VALUES (41, '应用题', '2', NULL, NULL, NULL, NULL, '2', '2024-12-22 15:58:38', 30, 93);
INSERT INTO `topic` VALUES (42, '选择题', '微软为其', '1', '1', '1', '1', 'A', '2024-12-22 16:06:00', 30, 94);
INSERT INTO `topic` VALUES (42, '判断题', '11111', '正确', '错误', NULL, NULL, '正确', '2024-12-22 16:06:00', 30, 95);
INSERT INTO `topic` VALUES (42, '应用题', '1324234', NULL, NULL, NULL, NULL, '213312', '2024-12-22 16:06:00', 40, 96);
INSERT INTO `topic` VALUES (43, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-22 16:12:21', 10, 97);
INSERT INTO `topic` VALUES (43, '选择题', '2', '1', '2', '3', '4', '2', '2024-12-22 16:12:21', 10, 98);
INSERT INTO `topic` VALUES (43, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-22 16:12:21', 10, 99);
INSERT INTO `topic` VALUES (43, '判断题', '2', '正确', '错误', NULL, NULL, '错误', '2024-12-22 16:12:21', 10, 100);
INSERT INTO `topic` VALUES (43, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 16:12:21', 30, 101);
INSERT INTO `topic` VALUES (43, '应用题', '2', NULL, NULL, NULL, NULL, '2', '2024-12-22 16:12:21', 30, 102);
INSERT INTO `topic` VALUES (44, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 16:14:46', 100, 103);
INSERT INTO `topic` VALUES (45, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 16:18:26', 100, 104);
INSERT INTO `topic` VALUES (46, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 16:20:19', 100, 105);
INSERT INTO `topic` VALUES (47, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-22 16:21:49', 25, 106);
INSERT INTO `topic` VALUES (47, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-22 16:21:49', 25, 107);
INSERT INTO `topic` VALUES (47, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-22 16:21:49', 50, 108);
INSERT INTO `topic` VALUES (48, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-28 08:26:15', 25, 109);
INSERT INTO `topic` VALUES (48, '判断题', '2', '正确', '错误', NULL, NULL, '错误', '2024-12-28 08:26:15', 25, 110);
INSERT INTO `topic` VALUES (48, '应用题', '1+1', NULL, NULL, NULL, NULL, '1', '2024-12-28 08:26:15', 50, 111);
INSERT INTO `topic` VALUES (49, '应用题', '1+1', NULL, NULL, NULL, NULL, '2', '2024-12-28 08:29:15', 100, 112);
INSERT INTO `topic` VALUES (50, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-28 08:34:43', 50, 113);
INSERT INTO `topic` VALUES (50, '应用题', '1+1', NULL, NULL, NULL, NULL, '2', '2024-12-28 08:34:43', 50, 114);
INSERT INTO `topic` VALUES (51, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-28 08:40:53', 25, 115);
INSERT INTO `topic` VALUES (51, '判断题', '2', '正确', '错误', NULL, NULL, '错误', '2024-12-28 08:40:53', 25, 116);
INSERT INTO `topic` VALUES (51, '应用题', '1+1', NULL, NULL, NULL, NULL, '1', '2024-12-28 08:40:53', 50, 117);
INSERT INTO `topic` VALUES (52, '选择题', '1+1', '1', '2', '3', '4', 'B', '2024-12-28 19:45:47', 0, 118);
INSERT INTO `topic` VALUES (52, '选择题', '1', '2', '3', '4', '5', 'A', '2024-12-28 19:45:47', 0, 119);
INSERT INTO `topic` VALUES (52, '判断题', '111', '正确', '错误', NULL, NULL, '正确', '2024-12-28 19:45:47', 5, 120);
INSERT INTO `topic` VALUES (52, '判断题', '111', '正确', '错误', NULL, NULL, '错误', '2024-12-28 19:45:47', 5, 121);
INSERT INTO `topic` VALUES (52, '应用题', '111', NULL, NULL, NULL, NULL, '111', '2024-12-28 19:45:47', 8, 122);
INSERT INTO `topic` VALUES (53, '选择题', '1+1 = ', '1', '2', '3', '4', '2', '2024-12-28 20:58:54', 25, 123);
INSERT INTO `topic` VALUES (53, '判断题', '1+ 1 = 2', '正确', '错误', NULL, NULL, '正确', '2024-12-28 20:58:54', 25, 124);
INSERT INTO `topic` VALUES (53, '应用题', '1+2+3 = ', NULL, NULL, NULL, NULL, '6', '2024-12-28 20:58:54', 50, 125);
INSERT INTO `topic` VALUES (54, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-28 21:16:25', 100, 126);
INSERT INTO `topic` VALUES (55, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-28 21:17:08', 100, 127);
INSERT INTO `topic` VALUES (56, '选择题', '1+1=', '2', '3', '1', '4', '2', '2024-12-28 21:20:30', 25, 128);
INSERT INTO `topic` VALUES (56, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-28 21:20:30', 25, 129);
INSERT INTO `topic` VALUES (56, '应用题', '1+1 = ', NULL, NULL, NULL, NULL, '2', '2024-12-28 21:20:30', 50, 130);
INSERT INTO `topic` VALUES (57, '选择题', '1', '1', '2', '3', '4', '1', '2024-12-29 14:36:57', 25, 131);
INSERT INTO `topic` VALUES (57, '判断题', '1', '正确', '错误', NULL, NULL, '正确', '2024-12-29 14:36:57', 25, 132);
INSERT INTO `topic` VALUES (57, '应用题', '1', NULL, NULL, NULL, NULL, '1', '2024-12-29 14:36:57', 50, 133);
INSERT INTO `topic` VALUES (58, '选择题', '1+1', '1', '2', '3', '4', '2', '2024-12-30 08:23:42', 50, 134);
INSERT INTO `topic` VALUES (58, '选择题', '2+2', '1', '2', '3', '4', '4', '2024-12-30 08:23:42', 50, 135);
INSERT INTO `topic` VALUES (59, '选择题', '2+2', '1', '2', '3', '4', '4', '2024-12-30 08:25:57', 25, 136);
INSERT INTO `topic` VALUES (59, '判断题', '1+1=2？', '正确', '错误', NULL, NULL, '正确', '2024-12-30 08:25:57', 25, 137);
INSERT INTO `topic` VALUES (59, '应用题', '1+1', NULL, NULL, NULL, NULL, '2', '2024-12-30 08:25:57', 50, 138);
INSERT INTO `topic` VALUES (60, '选择题', '2+2=', '1', '2', '3', '4', '4', '2024-12-30 08:49:40', 25, 139);
INSERT INTO `topic` VALUES (60, '判断题', '1+1=2？', '正确', '错误', NULL, NULL, '正确', '2024-12-30 08:49:40', 25, 140);
INSERT INTO `topic` VALUES (60, '应用题', '3+3=', NULL, NULL, NULL, NULL, '6', '2024-12-30 08:49:40', 50, 141);
INSERT INTO `topic` VALUES (61, '选择题', '1+1=', '1', '2', '3', '4', '2', '2024-12-30 09:38:47', 25, 142);
INSERT INTO `topic` VALUES (61, '判断题', '1+1 = 2', '正确', '错误', NULL, NULL, '正确', '2024-12-30 09:38:47', 25, 143);
INSERT INTO `topic` VALUES (61, '应用题', '1 +1 =', NULL, NULL, NULL, NULL, '2', '2024-12-30 09:38:47', 50, 144);

SET FOREIGN_KEY_CHECKS = 1;
