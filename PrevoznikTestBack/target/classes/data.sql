
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

              
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUES (1, 'Vladike Cirica', 'Ogi bus', '123456');
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUES (2, 'Vladike Cirica 2', 'Luka bus', '654321');
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUES (3, 'Vladike Cirica 3', 'Milan bus', '334321');

INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (1, 40, 299.99, 'Novi Sad', '2022-07-23 22:00', 1);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (2, 41, 199.99, 'Novi Sad', '2022-07-23 22:00', 2);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (3, 40, 299.99, 'Subotica', '2022-07-23 22:00', 3);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (4, 42, 249.99, 'Novi Sad', '2022-07-23 22:00', 2);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (5, 40, 199.99, 'Subotica', '2022-07-23 22:00', 3);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (6, 43, 299.99, 'Backa Topola', '2022-07-23 22:00', 1);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (7, 25, 193.99, 'Backa Topola', '2022-06-21 20:00', 3);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (8, 25, 123.99, 'Backa Topola', '2022-06-21 20:00', 1);
INSERT INTO linije (id, broj_mesta, cena_karte, destinacija, polazak, prevoznik_id) VALUES (9, 30, 599.99, 'Subotica', '2022-07-21 21:00', 2);
