ALTER TABLE `User` ADD COLUMN `activation_hash` VARCHAR(255) NULL;
ALTER TABLE `User` ADD COLUMN `verified` TINYINT(1) NULL;