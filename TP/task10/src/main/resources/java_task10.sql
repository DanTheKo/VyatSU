
\! chcp 1251


CREATE TABLE student
(    id serial NOT NULL PRIMARY KEY,
     name text NOT NULL ,
     passport_series varchar(4) NOT NULL CHECK (passport_series~ '^[0-9]+$'),
     passport_number varchar(6) NOT NULL CHECK (passport_number~ '^[0-9]+$'),
     unique (passport_number, passport_series)
);


CREATE TABLE subject
(
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL
);
--DROP table public.progress;
--DROP table public.student cascade ;
--DROP table public.subject;
CREATE TABLE progress
(
    id serial NOT NULL PRIMARY KEY ,
    rating int NOT NULL,
    student_id int NOT NULL REFERENCES public.student(id) on delete cascade,
    subject_id int NOT NULL REFERENCES public.subject(id) on delete cascade,
    unique (subject_id, subject_id),
    check (rating <= 5 and rating >=2 )
);

insert into public.student (name, passport_series, passport_number)
values ('Даниил', '5432', '763751'),
       ('Иван', '5432', '563751'),
       ('Михаил', '5433', '263951'),
       ('Денис', '5432', '363751')
;



insert into public.subject (name)
values ('Математика'),
       ('Русский язык'),
       ('История'),
       ('Информатика');


insert into public.progress (rating, student_id, subject_id)
values (5, 2, 1),
       (4, 2, 2),
       (5, 2, 3),
       (2, 2, 4),
       (5, 3, 1),
       (4, 3, 2),
       (5, 3, 3),
       (5, 3, 4);

insert into public.progress (rating, student_id, subject_id)
values
       (5, 3, 4);

insert into public.progress (rating, student_id, subject_id)
values (3, 1, 3);

delete from progress where rating = 5 and student_id = 1 and subject_id = 3;

delete from progress where student_id != 1;

select stud.id, stud.name, stud.passport_series, stud.passport_number from public.student stud
    join public.progress prog
        on stud.id = prog.student_id
    join public.subject s
         on prog.subject_id = s.id
where rating > 3 and s.name = 'Математика';

select avg(rating) from public.progress prog
join public.subject
    on prog.subject_id = subject.id;

select avg(rating) from public.progress prog
    join public.student stud
        on prog.subject_id = stud.id
where stud.name = 'Даниил';

select sb.name, COUNT(sb.name)
from public.Progress p
         join public.Student s on p.student_id = s.id
         join public.Subject sb on p.subject_id = sb.id
group by sb.name
order by count(sb.name) DESC
limit 3;

-- select s.id, s.name
-- from public.student s
--          left join public.progress p on p.student_id = s.id
--          left join public.Subject sb on p.subject_id = sb.id
-- where rating is not null AND rating NOT IN (1, 2, 3)
-- group by s.id, s.name
-- having COUNT(p.rating) = COUNT(sb.*);


select *
from student s
where exists (
select *
from subject sb
    where not exists (
    select *
    from progress p
    where p.student_id = s.id
    and p.subject_id = sb.id
    and p.rating > 3)
);

insert into public.progress (rating, student_id, subject_id)
values (5, 3, 4);

delete from progress where rating = 3 and student_id = 1 and subject_id = 3;

insert into public.progress (rating, student_id, subject_id)
values (5, 1, 3);

delete from progress where rating = 5 and student_id = 1 and subject_id = 3;
delete from progress where id = 3;
