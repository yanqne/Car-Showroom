-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: autoshowroom
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `slug` varchar(140) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_brands_slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'toyota','test-1','2025-11-06 12:53:51'),(2,'Mercedes-Benz','test-2','2025-11-06 12:53:51'),(3,'Ford','test-3','2025-11-06 12:53:51'),(4,'VinFast','test-4','2025-11-06 12:53:51'),(5,'Honda','test-5','2025-11-06 12:53:51'),(8,'test222','hahahaahaha','2025-12-12 03:13:34');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `hex` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_colors_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'Đen','#000000'),(2,'Trắng','#FFFFFF'),(3,'Bạc','#C0C0C0'),(4,'Đỏ','#FF0000'),(5,'Xanh Dương','#0000FF'),(6,'Xám','#808080'),(7,'Cam','#FFA500');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leads`
--

DROP TABLE IF EXISTS `leads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leads` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `full_name` varchar(140) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `email` varchar(160) DEFAULT NULL,
  `message` text,
  `vehicle_id` bigint unsigned DEFAULT NULL,
  `source` enum('FACEBOOK','GOOGLE','PHONE_CALL','WALK_IN','WEBSITE') DEFAULT NULL,
  `status` enum('CONTACTED','LOST','NEW','QUALIFIED') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_leads_vehicle` (`vehicle_id`,`status`,`created_at`),
  CONSTRAINT `fk_leads_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leads`
--

LOCK TABLES `leads` WRITE;
/*!40000 ALTER TABLE `leads` DISABLE KEYS */;
INSERT INTO `leads` VALUES (1,'Nguyễn Văn An','0901234567','an.nguyen@email.com','Tôi muốn tư vấn về C300 AMG và giá lăn bánh.',5,'WEBSITE','NEW','2025-11-06 12:53:51'),(2,'Trần Thị Bích','0912345678','bich.tran@email.com','Báo giá Camry 2.5Q màu trắng.',1,'FACEBOOK','CONTACTED','2025-11-06 12:53:51'),(3,'Lê Minh Cường','0987654321','cuong.le@email.com','Hỏi về pin VF 8 Plus và chính sách thuê pin.',8,'GOOGLE','QUALIFIED','2025-11-06 12:53:51'),(4,'Phạm Thu Duyên','0934567890','duyen.pham@email.com','Tôi muốn đăng ký lái thử Ford Ranger Wildtrak.',7,'WEBSITE','NEW','2025-11-06 12:53:51'),(5,'Hoàng Văn Em','0978123456','em.hoang@email.com','Giá lăn bánh GLC 300 2024 tại Hà Nội?',6,'PHONE_CALL','LOST','2025-11-06 12:53:51'),(6,'Vũ Thị Gấm','0945678123','gam.vu@email.com','Corolla Cross 1.8V còn hàng không?',3,'WALK_IN','CONTACTED','2025-11-06 12:53:51'),(7,'Đặng Hữu Hùng','0905556677','hung.dang@email.com','Vui lòng liên hệ lại cho tôi.',NULL,'WEBSITE','NEW','2025-11-06 12:53:51'),(8,'Nguyễn Thị Kiều','0918889900','kieu.nguyen@email.com','So sánh C200 và C300 giúp tôi.',4,'GOOGLE','QUALIFIED','2025-11-06 12:53:51'),(9,'Bùi Văn Lâm','0988776655','lam.bui@email.com','Giá Vios G hiện tại bao nhiêu?',10,'FACEBOOK','NEW','2025-11-06 12:53:51'),(10,'Phan Thị Mai','0902223344','mai.phan@email.com','Tư vấn Ford Everest Titanium 4x4.',9,'WEBSITE','CONTACTED','2025-11-06 12:53:51'),(11,'adgcvzcv','1111111111111','adgadgzczc@gmail.com','adg',NULL,'WEBSITE','NEW','2025-12-26 22:30:26'),(12,'tao nè','0889816369','taonenha@gmail.com','tao nè',NULL,'WEBSITE','NEW','2025-12-26 22:34:24');
/*!40000 ALTER TABLE `leads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `models` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `brand_id` bigint unsigned NOT NULL,
  `name` varchar(160) NOT NULL,
  `segment` enum('CONVERTIBLE','COUPE','DEFAULT','HATCHBACK','MINIVAN','SEDAN','SUV','TRUCK','VAN') DEFAULT NULL,
  `slug` varchar(180) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_models_slug` (`slug`),
  KEY `idx_models_brand` (`brand_id`),
  CONSTRAINT `fk_models_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,1,'Camry','SEDAN','toyota-camry','2025-11-06 12:53:51'),(2,1,'Corolla Cross','SUV','toyota-corolla-cross','2025-11-06 12:53:51'),(3,2,'C-Class','SEDAN','mercedes-c-class','2025-11-06 12:53:51'),(4,2,'GLC','SUV','mercedes-glc','2025-11-06 12:53:51'),(5,3,'Ranger','TRUCK','ford-ranger','2025-11-06 12:53:51'),(6,4,'VF 8','SUV','vinfast-vf-8','2025-11-06 12:53:51'),(7,3,'Everest','SUV','ford-everest','2025-11-06 12:53:51'),(8,4,'VF 9','SUV','vinfast-vf-9','2025-11-06 12:53:51'),(9,1,'Vios','SEDAN','toyota-vios','2025-11-06 12:53:51'),(10,5,'Civic','SEDAN','honda-civic','2025-11-06 12:53:51'),(16,1,'hahaha','SEDAN','hahaha','2025-12-26 01:43:03'),(18,2,'adgadgaddga','HATCHBACK','1','2025-12-26 02:57:19');
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_drives`
--

DROP TABLE IF EXISTS `test_drives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_drives` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `lead_id` bigint unsigned NOT NULL,
  `vehicle_id` bigint unsigned NOT NULL,
  `preferred_at` date DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','CONFIRMED','PENDING') DEFAULT NULL,
  `note` varchar(300) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_test_drives_vehicle` (`vehicle_id`,`status`,`preferred_at`),
  KEY `fk_test_drives_lead` (`lead_id`),
  CONSTRAINT `fk_test_drives_lead` FOREIGN KEY (`lead_id`) REFERENCES `leads` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_test_drives_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_drives`
--

LOCK TABLES `test_drives` WRITE;
/*!40000 ALTER TABLE `test_drives` DISABLE KEYS */;
INSERT INTO `test_drives` VALUES (1,1,5,'2025-11-01','CONFIRMED','Khách hẹn 9h sáng T7 tại showroom.','2025-11-06 12:53:51'),(2,3,8,'2025-11-02','COMPLETED','Khách đã lái thử, rất hài lòng.','2025-11-06 12:53:51'),(3,4,7,'2025-11-03','PENDING','Chờ xác nhận lại thời gian với khách.','2025-11-06 12:53:51'),(4,6,3,'2025-10-29','COMPLETED','Khách walk-in và lái thử ngay.','2025-11-06 12:53:51'),(5,8,4,'2025-11-04','PENDING','Khách muốn lái thử C200 trước.','2025-11-06 12:53:51'),(6,8,5,'2025-11-04','PENDING','Lái thử C300 ngay sau C200 để so sánh.','2025-11-06 12:53:51'),(7,2,1,'2025-10-30','CANCELLED','Khách bận việc đột xuất, dời lịch.','2025-11-06 12:53:51'),(8,11,6,'2025-12-31','PENDING',NULL,'2025-12-26 22:30:26'),(9,11,6,'2025-12-31','PENDING',NULL,'2025-12-26 22:31:16'),(10,12,14,'2025-12-31','PENDING',NULL,'2025-12-26 22:34:24');
/*!40000 ALTER TABLE `test_drives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_colors`
--

DROP TABLE IF EXISTS `vehicle_colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_colors` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigint unsigned NOT NULL,
  `color_id` bigint unsigned NOT NULL,
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_vehicle_color_pair` (`vehicle_id`,`color_id`),
  KEY `idx_vehicle_colors_default` (`vehicle_id`,`is_default`),
  KEY `fk_vehicle_colors_color` (`color_id`),
  CONSTRAINT `fk_vehicle_colors_color` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_vehicle_colors_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_colors`
--

LOCK TABLES `vehicle_colors` WRITE;
/*!40000 ALTER TABLE `vehicle_colors` DISABLE KEYS */;
INSERT INTO `vehicle_colors` VALUES (1,1,1,1),(2,1,2,0),(3,1,4,0),(4,2,1,0),(5,2,3,1),(6,3,2,1),(7,3,5,0),(8,4,1,1),(9,4,2,0),(10,4,6,0),(11,5,4,1),(12,5,1,0),(13,6,2,1),(14,7,7,1),(15,7,1,0),(16,8,5,1),(17,8,2,0),(18,9,1,1),(19,10,3,1);
/*!40000 ALTER TABLE `vehicle_colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_images`
--

DROP TABLE IF EXISTS `vehicle_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_images` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigint unsigned NOT NULL,
  `url` varchar(700) NOT NULL,
  `alt` varchar(200) DEFAULT NULL,
  `sort_order` int unsigned DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_images_vehicle` (`vehicle_id`,`sort_order`),
  CONSTRAINT `fk_vehicle_images_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_images`
--

LOCK TABLES `vehicle_images` WRITE;
/*!40000 ALTER TABLE `vehicle_images` DISABLE KEYS */;
INSERT INTO `vehicle_images` VALUES (1,1,'https://example.com/images/camry-q-1.jpg','Ngoại thất Toyota Camry 2.5Q',0,'2025-11-06 12:53:51'),(2,1,'https://example.com/images/camry-q-2.jpg','Nội thất Toyota Camry 2.5Q',1,'2025-11-06 12:53:51'),(3,1,'https://example.com/images/camry-q-3.jpg','Hàng ghế sau Camry 2.5Q',2,'2025-11-06 12:53:51'),(4,3,'https://example.com/images/cross-v-1.jpg','Ngoại thất Corolla Cross 1.8V',0,'2025-11-06 12:53:51'),(5,3,'https://example.com/images/cross-v-2.jpg','Nội thất Corolla Cross 1.8V',1,'2025-11-06 12:53:51'),(6,5,'https://example.com/images/c300-1.jpg','Ngoại thất C300 AMG',0,'2025-11-06 12:53:51'),(7,5,'https://example.com/images/c300-2.jpg','Nội thất C300 AMG',1,'2025-11-06 12:53:51'),(8,5,'https://example.com/images/c300-3.jpg','Vô lăng C300 AMG',2,'2025-11-06 12:53:51'),(9,7,'https://example.com/images/ranger-1.jpg','Ngoại thất Ford Ranger Wildtrak',0,'2025-11-06 12:53:51'),(10,7,'https://example.com/images/ranger-2.jpg','Thùng xe Ford Ranger Wildtrak',1,'2025-11-06 12:53:51'),(11,8,'https://example.com/images/vf8-1.jpg','Ngoại thất VinFast VF 8',0,'2025-11-06 12:53:51');
/*!40000 ALTER TABLE `vehicle_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_specs`
--

DROP TABLE IF EXISTS `vehicle_specs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_specs` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigint unsigned NOT NULL,
  `spec_key` varchar(255) DEFAULT NULL,
  `spec_value` longtext,
  `sort_order` int unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_specs_vehicle` (`vehicle_id`,`sort_order`),
  CONSTRAINT `fk_vehicle_specs_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_specs`
--

LOCK TABLES `vehicle_specs` WRITE;
/*!40000 ALTER TABLE `vehicle_specs` DISABLE KEYS */;
INSERT INTO `vehicle_specs` VALUES (1,1,'Số chỗ ngồi','5',0),(2,1,'Kiểu động cơ','2.5L Dynamic Force',1),(3,1,'Hộp số','Tự động 8 cấp (8AT)',2),(4,1,'Cửa sổ trời','Có (Toàn cảnh)',3),(5,3,'Số chỗ ngồi','5',0),(6,3,'Kiểu động cơ','Hybrid (Xăng 1.8L + Điện)',1),(7,3,'Hộp số','Tự động vô cấp (CVT)',2),(8,3,'Gói an toàn','Toyota Safety Sense 2.0',3),(9,5,'Số chỗ ngồi','5',0),(10,5,'Kiểu động cơ','2.0L Turbo (EQ Boost)',1),(11,5,'Hộp số','Tự động 9 cấp 9G-TRONIC',2),(12,5,'Hệ thống treo','Thể thao (Sport)',3),(13,7,'Số chỗ ngồi','5',0),(14,7,'Kiểu động cơ','2.0L Bi-Turbo Diesel',1),(15,7,'Hộp số','Tự động 10 cấp (10AT)',2),(16,7,'Khả năng lội nước','800mm',3),(17,8,'Quãng đường (WLTP)','420km / 1 lần sạc',0),(18,8,'Pin','82 kWh (Khả dụng)',1),(19,8,'Màn hình','15.6 inch',2);
/*!40000 ALTER TABLE `vehicle_specs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `model_id` bigint unsigned NOT NULL,
  `name` varchar(200) NOT NULL,
  `variant` varchar(160) DEFAULT NULL,
  `year` smallint unsigned DEFAULT NULL,
  `drivetrain` varchar(60) DEFAULT NULL,
  `transmission` varchar(60) DEFAULT NULL,
  `engine` varchar(120) DEFAULT NULL,
  `power_hp` smallint unsigned DEFAULT NULL,
  `torque_nm` smallint unsigned DEFAULT NULL,
  `fuel_consumption` varchar(40) DEFAULT NULL,
  `seats` tinyint unsigned DEFAULT NULL,
  `dimensions` varchar(120) DEFAULT NULL,
  `trunk_l` smallint unsigned DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE','SOLD') NOT NULL,
  `description` text,
  `thumbnail_url` varchar(600) DEFAULT NULL,
  `price_vnd` bigint unsigned DEFAULT NULL,
  `slug` varchar(200) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_vehicles_slug` (`slug`),
  KEY `idx_vehicles_model` (`model_id`),
  KEY `idx_vehicles_price` (`price_vnd`),
  CONSTRAINT `fk_vehicles_model` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (1,1,'Toyota Camry 2.5Q','2.5Q',2024,'FWD','8-speed AT','2.5L Dynamic Force',207,250,'7.1L/100km',5,'4885x1840x1445mm',524,'ACTIVE','Phiên bản cao cấp nhất của Toyota Camry 2024.','https://example.com/images/camry-q-thumb.jpg',1405000000,'toyota-camry-2-5q-2024','2025-11-06 12:53:51',NULL),(2,1,'Toyota Camry 2.0G','2.0G',2024,'FWD','CVT','2.0L',170,206,'6.4L/100km',5,'4885x1840x1445mm',524,'ACTIVE','Phiên bản tiêu chuẩn của Toyota Camry.','https://example.com/images/camry-g-thumb.jpg',1105000000,'toyota-camry-2-0g-2024','2025-11-06 12:53:51',NULL),(3,2,'Toyota Corolla Cross 1.8V','1.8V (Hybrid)',2024,'FWD','CVT','1.8L Hybrid',122,142,'4.2L/100km',5,'4460x1825x1620mm',440,'ACTIVE','Mẫu SUV đô thị bán chạy với động cơ Hybrid tiết kiệm.','https://example.com/images/cross-v-thumb.jpg',905000000,'toyota-cross-1-8v-2024','2025-11-06 12:53:51',NULL),(4,3,'Mercedes-Benz C200 Avantgarde','Avantgarde',2023,'RWD','9G-TRONIC','1.5L Turbo (EQ Boost)',204,300,'6.8L/100km',5,'4751x1820x1438mm',455,'INACTIVE','Sedan hạng sang với thiết kế trẻ trung.','https://example.com/images/c200-thumb.jpg',1669000000,'mercedes-c200-avantgarde-2023','2025-11-06 12:53:51','2025-12-26 01:51:50'),(5,3,'Mercedes-Benz C300 AMG','AMG Line',2023,'RWD','9G-TRONIC','2.0L Turbo (EQ Boost)',258,400,'7.5L/100km',5,'4751x1820x1438mm',455,'SOLD','Phiên bản thể thao C-Class với gói AMG.','https://example.com/images/c300-thumb.jpg',2199000000,'mercedes-c300-amg-2023','2025-11-06 12:53:51',NULL),(6,4,'Mercedes-Benz GLC 300 4MATIC','300 4MATIC',2024,'AWD','9G-TRONIC','2.0L Turbo',258,400,'8.2L/100km',5,'4716x1890x1640mm',620,'ACTIVE','SUV hạng sang bán chạy nhất của Mercedes tại VN.','https://example.com/images/glc300-thumb.jpg',2799000000,'mercedes-glc-300-4matic-2024','2025-11-06 12:53:51',NULL),(7,5,'Ford Ranger Wildtrak 2.0L 4x4','Wildtrak 4x4',2024,'4x4','10-speed AT','2.0L Bi-Turbo Diesel',210,500,'8.0L/100km',5,'5370x1918x1884mm',1233,'ACTIVE','Vua bán tải với phiên bản Wildtrak cao cấp.','https://example.com/images/ranger-wildtrak-thumb.jpg',979000000,'ford-ranger-wildtrak-2024','2025-11-06 12:53:51',NULL),(8,6,'VinFast VF 8 Plus','Plus (1 Pin)',2024,'AWD','Single-speed','Dual-Motor Electric',402,620,'N/A',5,'4750x1934x1667mm',376,'ACTIVE','Xe điện SUV hạng D của VinFast, phiên bản Plus.','https://example.com/images/vf8-plus-thumb.jpg',1290000000,'vinfast-vf-8-plus-2024','2025-11-06 12:53:51',NULL),(9,7,'Ford Everest Titanium+ 2.0L 4x4','Titanium+ 4x4',2023,'4x4','10-speed AT','2.0L Bi-Turbo Diesel',210,500,'8.5L/100km',7,'4914x1923x1842mm',259,'ACTIVE','SUV 7 chỗ khung gầm rời mạnh mẽ.','https://example.com/images/everest-titanium-thumb.jpg',1468000000,'ford-everest-titanium-plus-2023','2025-11-06 12:53:51',NULL),(10,9,'Toyota Vios G','G',2024,'FWD','CVT','1.5L',106,140,'5.9L/100km',5,'4425x1730x1475mm',506,'INACTIVE','Mẫu xe quốc dân, bền bỉ và tiết kiệm.','https://example.com/images/vios-g-thumb.jpg',592000000,'toyota-vios-g-2024','2025-11-06 12:53:51',NULL),(11,1,'1','1',2025,'1','1','1',1,1,'1',5,'1',1,'ACTIVE','1','1',1,'1','2025-12-12 01:05:28','2025-12-12 01:05:28'),(13,3,'zcbzcbzcb','132145',2025,'1245','1245','1245',123,1245,'1245',2,'1245',245,'ACTIVE','adczbzcbcz','14251425afg',135135135,'zcbzcbzcb','2025-12-26 08:53:15','2025-12-26 08:53:15'),(14,4,'12312','113',2025,'135','135','135',135,135,'135',5,'135135',135,'ACTIVE','135135','1351531da',135135,'12312','2025-12-26 08:53:34','2025-12-26 08:53:34');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-27 15:49:18
