INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (1, 'Jan', 'Ambroży', '123456789', 'jambrozy@gmail.com', 'DOC123', 'CARDIOLOGY'),
       (2, 'Adam', 'Nowak', '921001122', 'adam.nowak@gmail.com', 'DOC124', 'PEDIATRICS');

INSERT INTO patient (first_name, last_name, date_of_birth, telephone_number, email, patient_number, doctor_id)
VALUES ('Anna', 'Nowak', '1980-01-01', '987654321', 'anowak@gmail.com', 'PAT123', 1),
       ('Ewa', 'Andrzejowska', '1982-02-15', '921876345', 'ewa.andrzejowska@gmail.com', 'PAT124', 1),
       ('Adam', 'Nowak', '1991-07-22', '921001122', 'adam.nowak@gmail.com', 'PAT125', 2);

INSERT INTO medical_treatment (treatment_description, type)
VALUES ('Badanie krwi', 'TEST');

INSERT INTO visit (description, time, doctor_id, patient_id, medical_treatment_id)
VALUES ('Wizyta kontrolna', '2024-05-03 10:00:00', 1, 1, 1),
       ('Wizyta kontrolna', '2024-06-03 14:00:00', 1, 2, 1),
       ('Konsultacja', '2024-06-10 16:00:00', 2, 3, 1),
       ('Badanie krwi', '2024-06-15 09:00:00', 2, 3, 1);

INSERT INTO address (address_line1, address_line2, city, postal_code, doctor_id)
VALUES ('Ulica 123', 'Mieszkanie 1', 'Wrocław', '00-001', 1);
