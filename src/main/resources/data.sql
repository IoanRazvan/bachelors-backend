insert into PROGRAMMING_LANGUAGE values('cpp', 'C++');
insert into PROGRAMMING_LANGUAGE values('javascript', 'Javascript');
insert into PROGRAMMING_LANGUAGE values('java', 'Java');

insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Array');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'String');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Hash Table');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Dynamic Programming');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Math');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Sorting');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Depth-First Search');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Greedy');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Breadth-First Search');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Tree');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Binary Search');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Matrix');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Binary Tree');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Two Pointers');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Bit Manipulation');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Stack');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Heap');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Graph');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Backtracking');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Linked List');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Monotonic Stack');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Trie');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Binary Search Tree');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Divide and Conquer');
insert into CATEGORY
values ((select nextval('HIBERNATE_SEQUENCE')), 'Queue');


COMMIT;