CREATE TABLE member
(
  id VARCHAR(8) not null,
  created_date datetime,
  updated_date datetime,
  name VARCHAR(255) NOT NULL,
  blog_link VARCHAR(255)	NOT NULL,
  primary key (id)
) engine=InnoDB;

CREATE TABLE post
(
  id VARCHAR(8) not null,
  created_date datetime,
  updated_date datetime,
  member_id VARCHAR(8) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description TEXT	NOT NULL,
  primary key (id)
) engine=InnoDB;

CREATE TABLE comment
(
  id bigint not null,
  created_date datetime,
  updated_date datetime,
  post_id VARCHAR(8)  NOT NULL,
  content VARCHAR(255)	NOT NULL,
  primary key (id)
) engine=InnoDB;

alter table post
   add constraint fk_post_to_member
   foreign key (member_id)
   references member (id);

alter table comment
   add constraint fk_comment_to_post
   foreign key (post_id)
   references post (id);