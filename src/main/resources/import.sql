INSERT INTO marcas (nombre) VALUES ('Seat');
INSERT INTO marcas (nombre) VALUES ('BMW');
INSERT INTO marcas (nombre) VALUES ('Mercedes');
INSERT INTO marcas (nombre) VALUES ('Audi');
INSERT INTO marcas (nombre) VALUES ('Volswagen');

INSERT INTO modelos (nombre,marca_id) VALUES ('127',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('Ibiza',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('Leon',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('M3',2);
INSERT INTO modelos (nombre,marca_id) VALUES ('Compact',2);
INSERT INTO modelos (nombre,marca_id) VALUES ('A3',4);
INSERT INTO modelos (nombre,marca_id) VALUES ('A8',4);
INSERT INTO modelos (nombre,marca_id) VALUES ('SLR',3);
INSERT INTO modelos (nombre,marca_id) VALUES ('Clase A',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('500',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('127',1);
INSERT INTO modelos (nombre,marca_id) VALUES ('Bettle',5);

INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (1,'rojo','2354ASD',2000,220);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (2,'amarillo','4444AAA',1500,180);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (3,'veerde','7777CGH',1700,210);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (4,'blanco','5432FRG',4300,300);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (5,'azul','8888BBB',2200,220);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (6,'negro','1111AWE',2500,200);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (7,'rojo','2234KIL',1500,190);
INSERT INTO coches (modelo_id,color,matricula,cilindrada,velocidad) VALUES (7,'azul','8778GHG',1700,210);



INSERT INTO usuarios (username,password,enabled) VALUES ('pedro','$2a$10$XLB7XJfTRbzEbuWytttRieF5y3pQ53UAtTb1wg/nmpNLBmWY0qGMy',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin','$2a$10$9xdj.D8WQR9wVQHqyjuh1O1vOwOdiTb2lKjPy2EAWpkErAGkefh0C',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre)VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id)VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id)VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id)VALUES(2,1);