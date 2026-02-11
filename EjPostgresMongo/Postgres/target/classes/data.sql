-- Insertar Partes
INSERT INTO partes (idpelicula, nombre, anio) VALUES (1, 'Phantom Blood', 1987) ON CONFLICT (idpelicula) DO NOTHING;
INSERT INTO partes (idpelicula, nombre, anio) VALUES (2, 'Battle Tendency', 1988) ON CONFLICT (idpelicula) DO NOTHING;
INSERT INTO partes (idpelicula, nombre, anio) VALUES (3, 'Stardust Crusaders', 1989) ON CONFLICT (idpelicula) DO NOTHING;

-- Insertar Personajes
INSERT INTO personajes (idactor, nombre, nacionalidad, idpelicula) VALUES (1, 'Jonathan Joestar', 'British', 1) ON CONFLICT (idactor) DO NOTHING;
INSERT INTO personajes (idactor, nombre, nacionalidad, idpelicula) VALUES (2, 'Joseph Joestar', 'British-American', 2) ON CONFLICT (idactor) DO NOTHING;
INSERT INTO personajes (idactor, nombre, nacionalidad, idpelicula) VALUES (3, 'Jotaro Kujo', 'Japanese', 3) ON CONFLICT (idactor) DO NOTHING;
INSERT INTO personajes (idactor, nombre, nacionalidad, idpelicula) VALUES (4, 'Dio Brando', 'British', 1) ON CONFLICT (idactor) DO NOTHING;

-- Actualizar la secuencia para que el siguiente ID autogenerado sea mayor que los insertados manualmente.
SELECT setval('partes_idpelicula_seq', (SELECT MAX(idpelicula) FROM partes), true);
SELECT setval('personajes_idactor_seq', (SELECT MAX(idactor) FROM personajes), true);
