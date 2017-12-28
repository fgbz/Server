prompt PL/SQL Developer import file
prompt Created on 2017��12��15�� by yuhangc
set feedback off
set define off
prompt Disabling triggers for SYS_ORGANIZATION...
alter table SYS_ORGANIZATION disable all triggers;
prompt Deleting SYS_ORGANIZATION...
delete from SYS_ORGANIZATION;
commit;
prompt Loading SYS_ORGANIZATION...
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('664348f2-cedb-4d61-8f84-2bd877e97440', '�����ٸ۾��ÿ�����������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('cee9769e-f596-40f9-b992-391e6e642fd7', '���ʸ��¼�����ҵ������������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('cf25d659-5c1b-4819-abfe-db571d1dc496', 'ң������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', 20, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('960f2a7b-14bc-4935-8a74-8f632b4433e5', '�ؼ�ϵͳά��', 1, 'e0ac0fee-8ca0-4771-9d5a-f0c1b198fdea', null, 1, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e0ac0fee-8ca0-4771-9d5a-f0c1b198fdea', 'ϵͳά��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 21, 2, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('28d94c2a-16a2-42fa-a6ba-cf34767f976d', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5b1efd5c-2826-4974-9e2b-f048fe2762a8', 'ʡ�����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', 22, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ba4c5102-cd76-4f10-80ac-b4fe3b9a6b2f', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c777c754-47f8-4947-a13e-29f9ccae2933', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('afc2713c-b107-48fc-8bd6-625e2691c282', '������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('13eb06ad-9021-42ff-aff5-f4d1fed8eda9', '����ӥ', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('432d0828-9c70-442d-8187-37414cb7029a', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('65b9b0b2-7386-4325-9a19-443da6de020d', 'xushiyang', 1, 'e0ac0fee-8ca0-4771-9d5a-f0c1b198fdea', null, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f10d0937-b63e-4958-bee1-eba095b3ec7d', '��ҫ��', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('58950dec-df2f-4ea3-8b0d-7ec993346dc1', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('55cc21da-7a80-4d51-85b7-fb1dd69455b8', '��־��', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('177927bb-0972-40c1-b750-44e92d80c5a6', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3c37da05-300a-4c1c-bba6-836198c84cf1', '�Ͳ���_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3ead40bd-0d27-40f6-9cb3-5ebdd7d2c10d', '�����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b83edc70-10bb-44d6-ba8f-5bec7ae36076', '������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('dcdb2a20-d668-45f2-b075-48c36af3c997', '��ϼ', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('07aac971-1552-418c-b2ea-946c7756490a', '������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b214ecc2-f476-4a82-8b65-1d8c4d88f616', '�ؼ�ϵͳά��-������', 1, 'e0ac0fee-8ca0-4771-9d5a-f0c1b198fdea', 0, 1, 3);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b746f769-04f6-4238-ab71-ceba9923bce5', '�ؼ�ϵͳά��-�����', 1, 'e0ac0fee-8ca0-4771-9d5a-f0c1b198fdea', null, 1, 3);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a74fead3-91b1-4f88-ad93-04790621dcfc', '��С��', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1a395ee8-1f2e-4c7b-8bb6-5e793f4b80a2', '��Ӫ��_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('955064ff-d753-40a8-a267-0fb58052a3b0', '�ൺ��_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('42369f96-a381-4665-b168-39a156705819', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('eec7f888-e01a-479e-9973-6ee11df69f68', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1ec0ae0d-103d-4136-829d-22965aa04d96', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3b51ad1b-1f2a-4317-8889-8445ea0f5d80', '�ĳ���_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0f7374aa-9ddf-47de-8fe9-3d8b704e8dd8', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('93980058-ed98-4c8e-9fca-7f20a6284e37', 'Ϋ����_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('21047711-da3a-4623-8c6c-ea20ede4b772', '��̨��_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5eacfa8f-bc9d-462d-a20b-edaa2cbfa7af', '��ׯ��_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('59e41e11-8549-4e77-b9fe-3012f75140dd', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f129fcb2-108a-4d8a-b5b3-94813d1916fc', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b55fd8f6-e353-4672-bd85-6fdd787f0b25', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('00000000-0000-0000-0000-000000000000', '�������鲿��', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', 0, 3, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5e15dd1a-f107-4095-b0d4-a213a2bf62d9', '������ɽ���ζȼ���', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 0, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('4666ce64-7e21-43bd-9ffe-eccd01a14828', '������_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c882b39e-c2d9-472d-a81e-58bb4ecee523', '����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c79f7e23-3350-46f2-8e44-ca3523aeb8a5', '������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 3);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0d25c541-4d14-4576-9d43-515be85dcf4e', '��Ρ', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2760281a-021d-4c49-b0eb-ddc1d3fc7237', '�����', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c331b086-3da9-4a3c-940c-f4be4dc95c06', 'ң������', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('98bf8520-2bf0-4d06-b229-fc04ebf1a459', '̩����_YS', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', null, 3, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3eb8d39d-2287-448e-b6e3-b5f15b42c9ab', '��ɽ��������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f3e595be-05aa-4b6a-be35-cfdf25d2dadd', '��ׯ��������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d0b4a3af-b9f5-4f42-8dd5-43f1c3804871', '�Ӷ���������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6625806a-7ef7-41f9-a000-a4205801ad55', '�����ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c1b47c21-2d8d-40cf-ad6e-78e5e49fb070', '۰���ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0db59ec0-d565-42be-b04c-30d5c9c0e6c8', '��ˮ�ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('47bbb3ca-a6fc-4b39-990f-2e2e2e703ec0', '�����ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('01d6edbd-74f0-48cd-821d-e5c6da5cf9ce', '���ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('322211dc-3ab0-452a-a9fb-dbb959347a80', 'ƽ���ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 12, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b56423e5-c0d3-4cbf-9f97-9e74f836dcbd', '�����ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 13, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b43123c0-3a1f-4cfa-b7d1-3c647b211e2e', '�����ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 14, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e0ae6538-44d4-4436-a8c3-f8f3737ad00c', '�����ع�����Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 15, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d321b854-40d6-4d25-ae72-ce5415fac408', 'Ϋ���й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 2, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f7b2c613-e401-4922-8348-95b7b5c8135d', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 4, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('526da45f-ff8e-4fe0-959d-3f4162cfc391', '��ɽ��������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('40c5b52b-6d6d-4d3b-b94a-d430cb172b8c', '�б���������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c6c5d897-e12c-4e32-8b16-60943bf8e74e', '������������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('26a8d23d-6382-499f-9cbc-f97db4a81348', '��ī�й�����Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ea9e402f-597d-4be3-a623-d144fb64eb29', '�����й�����Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3025d639-88b7-461f-a3b9-f5584f885fe9', '�������¼�����ҵ������������Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a5d4bcdc-db1f-4ec7-8d2e-a85c73235959', '�γ���������Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('910fe7de-eb56-48c8-a3b0-84e70382ecdf', '��̨�ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2ad1d7fc-2125-40dd-9a25-e8ffc83ad290', '�����ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('4867eb7c-01db-4bee-bd02-7191e3c9874e', '��ˮ�ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8e60910b-84d0-4099-ab59-5b4a6a1ae00f', '�����й�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 12, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d03c2e77-621e-4a85-9e3a-2269ac237998', '�����˺Ӿ��ÿ�����������Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ae9646b7-258f-41f9-a5f2-808215e20e9d', '���ݾ��ü���������������Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('192eb7f1-aed8-45ea-8544-323c00bb5ade', '�³���������Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6b9bf657-32aa-4043-ac8b-0de4fecd1d4c', '�����������Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6c63a200-fcc8-4754-b17d-672f5f28cc81', '�����ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f1af82c3-f4dd-4aac-b0f4-1c31a43843cf', '�����ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8e2c58c4-8275-4b3a-ace4-c22e4e79e30d', '�����ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e3b1e665-6643-4a16-b94c-7df16f0980d8', '����ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ad8c36e6-9cf4-48ff-8d46-d2e1244683dd', 'ƽԭ�ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d52ab3b1-da59-46b2-bfe2-4f9b8734dbf9', '�Ľ��ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d7cc6b4b-27f1-4f8b-8614-ce34c7f56c14', '����ع�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0fed3420-d050-422d-a7a4-253d540c42e6', '�����й�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 12, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f4c4556d-7687-49fb-8e79-51df2668118c', '����й�����Դ��', 1, 'a31b77fb-2098-4e1c-9264-c20c97e92e66', 13, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a1a5cff6-09b8-4cab-9ab3-7347241c76ac', '�ĳǾ��ü���������������Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e06e2221-71f5-476c-9497-b95081079740', '��������������Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5f49174f-cefe-47f6-b604-42eb4173fea3', 'ݷ�ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('07fe6280-0759-4150-bf81-08bdf20c301e', '�����ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('df10656d-2cc4-4c6b-8369-dbd88745dc0a', '�����ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('108d2c65-a9a2-4318-a32c-51e564dabd6e', '��̨�й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 3, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('53811ece-0052-4875-8ace-8dde6445c79d', '��̨���ü���������������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('651621af-63cb-4726-b9cb-6f5feeb4df7a', '��̨���¼�����ҵ������������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('746d5e2d-d382-4703-a60c-2649d2f5387b', '֥���������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', '��Ӫ�й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 1, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('60797dd8-8744-4a68-96cc-6e9138e29310', '��ɽ��������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('61e00773-f620-4029-8902-d6115eb0491a', 'Ĳƽ��������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ed90e4e3-7aab-479d-938b-d33b591219b5', '��ɽ��������Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9d300e17-7202-4b97-932b-0ad13d96334e', '�����ع�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c34454d5-4df4-43e0-9086-efe602806f11', '�����й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 8, 1, null);
commit;
prompt 100 records committed...
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('eef47c80-a9da-4247-b076-1364ed20bf8b', '�����й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9c6bad34-6311-4228-96c1-0c0465c13b69', '�����й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('B9D58FB131282E42A1B63A550D3BCBF4', 'ɽ��ʡ������Դ��', 1, null, 0, 3, 3);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c9fe7594-ad34-4899-b5b5-6ae8ea1b9eeb', '�����й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3dddf262-cdb7-4932-b52d-52ef57fa35e6', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 1, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d3965089-f5ca-430b-b863-93f5306b3c18', '�ൺ�й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 2, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('96711792-1710-4f53-b4e4-27d70a425270', '��Զ�й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 12, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a446bded-58b2-4a8f-a324-5a2f97b62d8a', '��ϼ�й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 13, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('99616711-645b-44a0-b8b4-24558ab8c76a', '�ŷð�', 2, 'B9D58FB131282E42A1B63A550D3BCBF4', 0, 3, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('332a86ad-7537-442c-b971-c1d1f8ceb30b', '�����й�����Դ��', 1, '108d2c65-a9a2-4318-a32c-51e564dabd6e', 14, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('df666065-6b9d-4a2a-baec-efe86ccd8a78', '��Ӫ�۾��ÿ�����������Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9ffcf7cc-d3e1-42ba-acaa-d882ee930fce', '��Ӫ���ü���������������Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f4a5bc68-2db6-4299-b7dc-50ec65238f21', '��Ӫ��������Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8fdbecea-2b3d-4654-a3e4-a6a9da169db8', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 10, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('85b3264b-d964-4d4d-aa7d-431afe2cc431', '�ӿ���������Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('4a56ef07-aadd-4b64-820a-d8133106319c', '������������Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9500a889-6f34-48d3-8343-89fbadf4026f', '�����ع�����Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('74d4edca-d105-4d51-a9a4-3d459135cd6a', '�����ع�����Դ��', 1, 'ec71faaf-f47a-4f8b-ae9c-c38274f5dcd2', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', '��ׯ�й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 2, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9408bd81-43d2-4e9e-ac9d-edd65890c5ac', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 3, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a3fcac8c-53aa-4daf-869b-5094242d3f94', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 4, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('492ad34a-ce40-4430-b85e-7330f7c423af', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('aad46608-cd36-4bbd-9d6e-27f9b0e2f3e2', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9b7a19a5-13c0-4b0d-a723-02727d09edc6', '����������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('bcc8e8fe-cdbf-4f6e-b3fd-b51fc0a8cd78', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e0ca4c43-a693-4d61-a84d-ff46c90dc619', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('cee98dca-cf46-4fcf-a35f-5ce37c7c2042', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d661d687-5c82-44bd-818c-9ff4de16dc46', 'ƽ���ع�����Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f28c629a-a668-42e1-8281-17eb7a55646a', '�����ع�����Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d44524da-1594-45d6-b6ab-7e75d2d296b2', '�̺��ع�����Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('28ca8641-d049-4a6e-84a0-8f73953bb9e0', '������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c7669c9e-a627-4ce6-8a5e-1bbc425fa3f1', '���ϸ��¼�����ҵ������������Դ��', 1, '3dddf262-cdb7-4932-b52d-52ef57fa35e6', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8d1f4e9e-d33d-49cf-a4c0-4994a74ad170', '������������Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 13, 1, 2);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1f1c0140-f26a-4f4b-bb3f-c415d50aafbf', '�޳��й�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 14, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e67d412f-471a-489c-b842-7b93677bf7a0', '��ׯ���¼�����ҵ������������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('232e719e-2c48-491c-a02e-b78293ba4e3c', '��ׯ������������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('706542d9-5b7e-4c7a-a3d1-42bb3693ba18', 'Ѧ����������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f84363d5-d6e7-47c7-84da-788ad9438aae', 'ỳ���������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e00c2d69-c2c4-4786-93b8-a660ab6551b8', '̨��ׯ��������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('7ddd0011-7388-43f5-8467-776d0f6e7584', 'ɽͤ��������Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a65c7805-97b8-4ea2-8d9f-eba06602be3f', '�����й�����Դ��', 1, '67e81d6f-d043-43fe-b8dc-5c8aa75e7b4d', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6885bf9b-79ea-465d-9869-35fff8d76392', '���վ��ü���������������Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b9914357-1d73-43cc-81e0-db20271eb9d4', '����ɽ�������ζȼ���������Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2f702e81-5ee3-4152-ae2f-ac2c0e807c27', '������������Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('dd4f9584-b214-4eb8-b1d9-9db3d34050d6', '�ɽ��������Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1143aafc-2a7b-4764-ac1c-28d31bf5dd2e', '�����ع�����Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a644d15f-abe3-440c-acc2-3185dbd178b9', '���ع�����Դ��', 1, '9408bd81-43d2-4e9e-ac9d-edd65890c5ac', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('7acfc31c-be4a-412d-b5e2-87b006f01215', '���߸��¼�����ҵ������������Դ��', 1, 'a3fcac8c-53aa-4daf-869b-5094242d3f94', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f212a3ac-e65b-47c1-b91e-e9ee10df94f9', '������ѩҰ������������Դ��', 1, 'a3fcac8c-53aa-4daf-869b-5094242d3f94', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c2c3485e-c498-4031-b5df-4551ad339d08', '����ũҵ���¼�����ҵʾ����������Դ��', 1, 'a3fcac8c-53aa-4daf-869b-5094242d3f94', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ccc769c7-8c41-45bb-8913-bce073625c45', '������������Դ��', 1, 'a3fcac8c-53aa-4daf-869b-5094242d3f94', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e6d3dfda-43f5-40b3-8c5e-fdf83b583fa0', '�ֳ���������Դ��', 1, 'a3fcac8c-53aa-4daf-869b-5094242d3f94', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('7f7280eb-9a51-491c-8977-f2191a2428e3', '���ݾ��ü���������������Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('418be92b-6070-4bfd-a45e-a25324c99757', '���ݸ��¼�����ҵ������������Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9766aee3-3414-43e7-a5b3-d362344e3ff5', '���ݱ������ÿ�����������Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('dd4a02f6-f3da-4cc8-be09-d8157cd59e95', '������������Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6fd931e0-7884-4f78-a418-de8dfaf136da', '�����ع�����Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('adfb2379-8ed3-4bb1-b16e-52453858c252', '�����ع�����Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ff8e4edc-0a56-4800-8679-391ef56c1080', '����ع�����Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('38b280b8-a323-4427-9f2b-da5b159579a0', 'մ����������Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('106e6699-303f-4084-9e4a-6e70145bce6d', '�����ع�����Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('14fd2f6e-cd28-4f72-ac57-db7157f8e827', '��ƽ�ع�����Դ��', 1, '125bdc35-8c8d-4486-abd9-95d4f8099e64', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ffa8a68e-e04f-4b1c-bae5-f568076dd96b', '�ʹ���������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('55a1299a-9058-4dd4-bf08-872ffb6c2930', '�ŵ���������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('05c69863-563b-4239-81f8-f402033b0922', '��ɽ��������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9ff934f8-ecd9-472b-8b61-50b99097e5ab', '������������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('932f984d-dd42-4146-8efe-e8a4404a18f4', '�ܴ���������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9fc80d6d-56de-4a4d-bfb9-6d64b107477a', '��̨�ع�����Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2118f955-4f8b-47b9-ad5c-18d15cb822c3', '�����ع�����Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('60f3784b-4cc1-4d0d-8a54-74df08d7c944', '��Դ�ع�����Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('36d39b72-0fcd-4428-8341-e563772f8cfc', '�Ͳ����Ĳ������ζȼ���������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('bdfb15b1-3c84-4dfe-8ae7-907fe0a89285', '�Ͳ����ÿ�����������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('39983eaf-9be4-4cba-955f-ac7552dcff8d', '�Ͳ����¼�����ҵ������������Դ��', 1, '6dad6476-742f-4791-bc9f-9d719ff1db64', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e0249651-24f1-473e-843f-b3b4ff1d3907', '̩ɽ�羰��ʤ��������Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8f851441-e97a-498e-8849-9e00224bdaf6', '̩�����¼�����ҵ������������Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2c44957e-a29f-40c9-9689-b4cc96e1d4c7', '̩ɽ��������Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ca4bcb5f-b2f3-49fc-80c3-5e2fa031b4b9', '�����������Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5cc817f1-30d2-4776-83ed-3f75fc44e6f3', '�����ع�����Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d76ee16f-feb0-4f31-aef0-45db455b3c3a', '��ƽ�ع�����Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('512173fe-4243-462e-9125-a8d416916a0b', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 2, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5a753e11-0a4c-4f29-89c8-d7c812702016', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 4, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a31b77fb-2098-4e1c-9264-c20c97e92e66', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 4, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', '�ĳ��й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 8, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('125bdc35-8c8d-4486-abd9-95d4f8099e64', '�����й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 7, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a0dba14c-3a13-42f4-be9e-b7e2cdb29f6e', '�����ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0bb9d31f-d56e-4eeb-8e88-7ea7e2552491', '��ʱ����', 2, '40c5b52b-6d6d-4d3b-b94a-d430cb172b8c', null, 1, 3);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c93648a5-7f71-47de-a19f-1f6d176b904a', '�ĳǸ��¼�����ҵ������������Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b8a43ae2-8e95-4ebf-b27a-74dd207c5e02', '�ĳǽ���ˮ�����ζȼ���������Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('18944aca-2f1a-49ee-9a2b-52bed5ffa95d', '��ƽ�ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6107164a-d7bf-426a-9d95-c816127eeec0', '���ع�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('fe0d396f-e52f-4962-9459-4bc368cb735e', '�����й�����Դ��', 1, '9f239e31-04b4-4bf3-aeb5-73afd7ee61d1', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2b32b433-38bb-409c-a3b7-498f5d86a02d', '��̩�й�����Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('31457d59-19cc-45ee-898e-89a434eea4ac', '�ʳ��й�����Դ��', 1, '60d05ff2-1174-43f0-97f0-f129e5e063f8', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('4816cd8c-f7fa-4c45-b7f0-c7d81a0bbdf5', 'Ϋ���ۺϱ�˰��������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('340008fb-97c5-46b2-a9cf-b4c683934baf', 'Ϋ��Ͽɽ��̬���ÿ�����������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1ef8dd2e-1d58-4be9-891d-7f03ef0124cf', 'Ϋ���������ü���������������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('bc888a3e-b080-4ade-9a31-5e1cb6870f19', 'Ϋ�����ÿ�����������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e8b39b19-07a8-48ef-aa9e-dfdb8c294cad', 'Ϋ�����¼�����ҵ������������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('24cc850b-978b-43a7-8404-343be2de0297', 'Ϋ����������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('02d548ad-5a0a-4a6b-888b-24332236218f', '��ͤ��������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 7, 1, null);
commit;
prompt 200 records committed...
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e8b49955-0799-4675-9b14-61ac0f6f1d2c', '������������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0d369787-3a0f-4918-aaec-308ba3c33a24', '������������Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c1d2b240-86e0-49f5-83da-d16b91789964', '�����ع�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('27e74a93-8b56-4b44-b49e-7b1772a80bcb', '�����ع�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('bd7ca7e9-a767-40a9-9b2b-c21c7c852b92', '�����й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 12, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('2703be01-ad7d-4eb7-80f5-41f27487688a', '����й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 13, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('22f65ff9-e67f-4e3b-84e3-44bcf447cbc4', '�ٹ��й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 14, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b1df6535-234b-42e3-ab0f-84dbfde80f32', '�����й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 15, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b6c8660a-dea8-45b8-87d7-9455be2a4c73', '�����й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 16, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3478e860-6d14-4471-9d76-3ff8ddd3a8d8', '�����й�����Դ��', 1, 'd321b854-40d6-4d25-ae72-ce5415fac408', 17, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('04428eeb-8daf-4102-afe0-cf028cb05770', '���󾭼ÿ�����������Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b51d3909-2091-416b-96b5-478ddc81ac81', '������¼�����ҵ������������Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8b7386e7-9b6c-4fba-9235-79c19e154df1', 'ĵ����������Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f3803af9-e5c1-4b57-9326-258139f8a87c', '���ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f901d4d7-ae3b-4c2a-ba13-777b36aaa7e9', '���ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b405a272-02e5-4d9a-8681-c091dbf569c7', '�����ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('358464ab-6f3d-4eb0-9983-9acccf4feff3', '��Ұ�ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('9aa5a621-6dca-4a82-8c09-e30fbe83a68f', '۩���ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 8, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('c31f3aed-2e08-472c-931c-c82594183b96', '۲���ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5e4664eb-5395-45c9-87a0-e1b8c69aa5d6', '������������Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 10, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('18972ee8-7b69-4cf2-9815-a8347f1ae0b7', '�����ع�����Դ��', 1, 'f7b2c613-e401-4922-8348-95b7b5c8135d', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('6dad6476-742f-4791-bc9f-9d719ff1db64', '�Ͳ��й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 1, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('60d05ff2-1174-43f0-97f0-f129e5e063f8', '̩���й�����Դ��', 1, 'B9D58FB131282E42A1B63A550D3BCBF4', 1, 2, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('a7a55bb3-1be0-493a-8652-e6a42662bca6', '������������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5cfae255-e7a7-47e1-ac0e-77073bf30b5e', '�����������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('0d0cb87e-3f33-4da2-8693-1cef8a3b3b15', '�Ƶ���������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d7fd46fd-4408-4377-a459-93abc33a74c3', '�����й�����Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e5c48756-ab1d-4133-acd5-23a615aa6811', 'ƽ���й�����Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('4d28d69c-ab00-4606-8522-ea7de9f64c0e', '�ൺ���¼�����ҵ������������Դ��', 1, 'd3965089-f5ca-430b-b863-93f5306b3c18', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f8dbcbb0-825f-42b3-ab0f-8140e0073f6d', '�������ÿ�����������Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('f21a7cc7-7e4f-4fd4-af34-97cb9734a3eb', '��������ʡ���ȼ���������Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('eb68eba5-6529-4568-ad22-931b312c77cb', '΢ɽ�ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('d46b647f-20ee-4d8d-8c96-275813383a0d', '�����ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('3ab67eac-155f-4cd5-b644-1b64aa215c7a', '�����ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 9, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('abe76fbc-cd86-4962-9134-c9f88756cde0', '��ɽ�ع�����Դ��', 1, '8fdbecea-2b3d-4654-a3e4-a6a9da169db8', 11, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('989c5aeb-70f3-4fb4-a5e8-047e30cbc2f6', '�����ٸ۾��ü���������������Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 1, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('8d5bcea7-a67e-4c1f-9177-9ac3eff02bf4', '�������ü���������������Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 2, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('1a0bbf37-8df1-43ee-b8ca-366da696d374', '�����߼�����ҵ������������Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 3, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('69fc1e19-f8d6-4147-ac83-efc7ec83156d', '������������Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 4, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('b8fd1c90-08d1-49de-827a-0233628ba69f', '�ĵ���������Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 5, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('ee204b4c-7462-4b7f-8409-1fb4fc6a9498', '�ٳ��й�����Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 6, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('5dbf9e82-6dd7-4e9f-8183-dba43e19991e', '��ɽ�й�����Դ��', 1, '512173fe-4243-462e-9125-a8d416916a0b', 7, 1, null);
insert into SYS_ORGANIZATION (id, name, type, parentid, rank, grade, maplayer)
values ('e755f18b-e41a-4518-926c-b74e845aeb2e', '���ʾ��ü���������������Դ��', 1, '5a753e11-0a4c-4f29-89c8-d7c812702016', 1, 1, null);
commit;
prompt 243 records loaded
prompt Enabling triggers for SYS_ORGANIZATION...
alter table SYS_ORGANIZATION enable all triggers;
set feedback on
set define on
prompt Done.
