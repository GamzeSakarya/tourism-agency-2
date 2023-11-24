# Tourism_Agency-2

## Proje Videosu
https://www.youtube.com/watch?v=RlY_uaa812g&ab_channel=gamzesakarya
   

## Proje Açıklaması
    Bu Projede amaç otomasyon sistemi yapmaktır. Kısaca
      Otel Yönetimi,
      Oda Yönetimi, 
      Dönem Yönetimi,
      Fiyat Yönetimi,
      Oda Arama,
      Rezervasyon işlemleri,
    gibi özellikleri barındırmaktadır.


## Installation Sql
``` sql
CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` enum('*','**','***','****','*****') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` text COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `reservation_info` (
  `id` int NOT NULL,
  `client_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `client_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `client_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adult_numb` int NOT NULL,
  `child_numb` int NOT NULL,
  `total_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;                                                                                                                         


CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `season_id` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL,
  `hotel_type_id` int NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `room_property` (
  `id` int NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `bed` enum('Double-2','Single-1') COLLATE utf8mb4_general_ci NOT NULL,
  `area` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` enum('Ultra Herşey Dahil','Herşey Dahil','Oda Kahvaltı','Tam Pansiyon','Yarım Pansiyon','Sadece Yatak','Alkol Hariç Full credit') COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

```