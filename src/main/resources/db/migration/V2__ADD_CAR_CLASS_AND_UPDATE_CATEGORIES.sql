ALTER TABLE cars ADD COLUMN car_class VARCHAR(50);
UPDATE cars SET car_class = 'ECONOMY' WHERE cars.car_class ISNULL;
ALTER TABLE cars ALTER COLUMN car_class SET NOT NULL;
UPDATE cars SET car_class = 'LUXURY', category = 'SEDAN' WHERE category = 'LUXURY';