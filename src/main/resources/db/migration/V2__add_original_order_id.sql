ALTER TABLE `order` ADD COLUMN IF NOT EXISTS original_order_id INTEGER NOT NULL UNIQUE;