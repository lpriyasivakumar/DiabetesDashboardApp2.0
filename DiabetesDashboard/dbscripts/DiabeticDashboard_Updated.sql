drop database if exists diabetic_dashboard_data;
create database diabetic_dashboard_data;

use diabetic_dashboard_data;

create table UserDB(
	UserID varchar(50) not null,    
    UserName varchar(35) not null,
    
    primary key(UserID)
);

create table timeofday(
	TimeOfDayID int not null auto_increment,
    TimeOfDayString varchar(20) not null,
    
    primary key(TimeOfDayID)
);

create table Reading(
	ReadingID int not null auto_increment,
	UserID varchar(50) not null,
	ReadingDate date not null ,
    TimeOfDayID int not null,
    BloodGlucose int not null,
    InsulinUnits int not null,
    
    primary key(ReadingID),
    foreign key(UserID) references UserDB(UserID),
    foreign key(TimeOfDayID) references timeofday(TimeOfDayID)
);

create table A1c(
	UserID varchar(50) not null,
    LabValue decimal (2,1),
    
    foreign key(UserID) references UserDB(UserID)
);

insert into timeofday(TimeOfDayString) 
	values('Before Breakfast'),
    ('After Breakfast'),
    ('Before Lunch'),
    ('After Lunch'),
    ('Before Dinner'),
    ('After Dinner'),
    ('Bedtime') ;