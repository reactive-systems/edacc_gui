ALTER TABLE `gridQueue` ADD COLUMN `numCPUsPerJob` INT NOT NULL DEFAULT 1 AFTER `numCPUs`;