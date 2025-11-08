create TABLE Usuario (
id varchar(6) primary key,
nombreComp varchar(100) not null,
nombreUser varchar(100) not null UNIQUE,
contrasenia varchar(100) not null
);

create table Recibos (
id varchar(6) primary key,
monto decimal(5),
descripcion varchar(100),
tipo varchar(50),
fecha date,
fechaVencimiento date,
numRUT varchar(100),
moneda varchar(100),
iVA decimal(5),
subtotal decimal(5),
debitoTarjeta varchar(100),
idUsuario varchar(6),
foreign key (idUsuario) references Usuario(id)
);