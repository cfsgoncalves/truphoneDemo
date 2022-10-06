/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  claudiog
 * Created: Oct 6, 2022
 */

CREATE TABLE device(
    id SERIAL PRIMARY KEY,
    name varchar(256),
    brand varchar(256),
    creation_time timestamp default current_timestamp
);