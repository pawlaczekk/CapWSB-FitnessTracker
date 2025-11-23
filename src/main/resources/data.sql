INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Wojciech', 'Nikiel', '2000-04-04', 'wojtek@nikiel.org');

INSERT INTO Statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (1, 25, 150.5, 12500);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (1, '2025-01-15', 75.5, 178.0, 72);

INSERT INTO event (name, description, start_time, end_time, country, city)
VALUES ('Park Run Krakow', 'Cotygodniowy bieg w parku', '2025-03-01 09:00:00', '2025-03-01 10:30:00', 'Poland', 'Krakow');

INSERT INTO event (name, description, start_time, end_time, country, city)
VALUES ('Warsaw Spring Ride', 'Pierwszy wiosenny rajd rowerowy', '2025-03-15 08:30:00', '2025-03-15 12:00:00', 'Poland', 'Warsaw');

INSERT INTO event (name, description, start_time, end_time, country, city)
VALUES ('Berlin City Walk', 'Spacer fitness po centrum', '2025-04-05 10:00:00', '2025-04-05 12:30:00', 'Germany', 'Berlin');

INSERT INTO event (name, description, start_time, end_time, country, city)
VALUES ('Prague Swim Cup', 'Zawody pływackie amatorów', '2025-05-20 11:00:00', '2025-05-20 15:00:00', 'Czechia', 'Prague');
