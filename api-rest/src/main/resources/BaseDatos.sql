-- Crear la tabla de Persona
CREATE TABLE Persona (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         genero VARCHAR(10),
                         edad INT,
                         identificacion VARCHAR(50) NOT NULL UNIQUE,
                         direccion VARCHAR(200),
                         telefono VARCHAR(20)
);

-- Crear la tabla de Cliente heredando de Persona
CREATE TABLE Cliente (
                         clienteid SERIAL PRIMARY KEY,
                         persona_id INT NOT NULL,
                         contraseña VARCHAR(100) NOT NULL,
                         estado VARCHAR(10),
                         FOREIGN KEY (persona_id) REFERENCES Persona(id)
);

-- Crear la tabla de Cuenta
CREATE TABLE Cuenta (
                        numero_cuenta SERIAL PRIMARY KEY,
                        tipo_cuenta VARCHAR(50) NOT NULL,
                        saldo_inicial DECIMAL(10, 2) NOT NULL,
                        estado VARCHAR(10),
                        cliente_id INT NOT NULL,
                        FOREIGN KEY (cliente_id) REFERENCES Cliente(clienteid)
);

-- Crear la tabla de Movimientos
CREATE TABLE Movimiento (
                            id SERIAL PRIMARY KEY,
                            fecha TIMESTAMP NOT NULL,
                            tipo_movimiento VARCHAR(50) NOT NULL,
                            valor DECIMAL(10, 2) NOT NULL,
                            saldo DECIMAL(10, 2) NOT NULL,
                            cuenta_id INT NOT NULL,
                            FOREIGN KEY (cuenta_id) REFERENCES Cuenta(numero_cuenta)
);

-- Insertar datos de ejemplo en la tabla de Persona
INSERT INTO Persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
                                                                                    ('Juan Pérez', 'Masculino', 30, '123456789', 'Calle Falsa 123', '555-1234'),
                                                                                    ('María Gómez', 'Femenino', 25, '987654321', 'Avenida Siempre Viva 456', '555-5678');

-- Insertar datos de ejemplo en la tabla de Cliente
INSERT INTO Cliente (persona_id, contraseña, estado) VALUES
                                                         (1, 'password123', 'Activo'),
                                                         (2, 'password456', 'Inactivo');

-- Insertar datos de ejemplo en la tabla de Cuenta
INSERT INTO Cuenta (tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
                                                                        ('Ahorros', 1000.00, 'Activo', 1),
                                                                        ('Corriente', 500.00, 'Activo', 2);

-- Insertar datos de ejemplo en la tabla de Movimientos
INSERT INTO Movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
                                                                             ('2024-01-05 10:00:00', 'Deposito', 500.00, 1500.00, 1),
                                                                             ('2024-01-10 15:30:00', 'Retiro', -200.00, 1300.00, 1),
                                                                             ('2024-01-15 09:00:00', 'Deposito', 200.00, 700.00, 2);
