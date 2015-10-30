ALTER TABLE `customer` ADD `created_at` timestamp NOT NULL AFTER `last_name`;
ALTER TABLE `customer` ADD `updated_at` timestamp NOT NULL AFTER `created_at`;
