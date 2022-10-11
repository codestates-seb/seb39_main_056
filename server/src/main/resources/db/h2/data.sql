INSERT INTO VEGETARIAN (vegetarian_type,levels)
VALUES ('프루테리언',1),
       ('비건', 2),
       ('오보', 3),
       ('락토', 3),
       ('락토-오보', 4),
       ('폴로', 5),
       ('페스코', 5),
       ('폴로-페스코', 6),
       ('플렉시테리언', 7);

INSERT INTO PRODUCT (product_name, price, stock_quantity, create_date, edit_date, thumbnail_image, detail_image, vegetarian_type)
VALUES ('아삭빨강 대추방울토마토', 9900, 100, '2022-10-01T10:11:12','2022-10-01T10:11:12', 'https://www.oasis.co.kr:48581/product/1663/detail/thumb_1663b2d53ccd-1403-47e7-8ee1-f9f593b5bb4e.jpg', 'https://www.oasis.co.kr:48580//se/2018/10/8/se_2f83f139-e44b-4aec-a464-37736735de8e.jpg', '프루테리언'),
       ('맛있는 단감', 16200, 100, '2022-10-01T10:11:14','2022-10-01T10:11:14', 'https://www.oasis.co.kr:48581/product/2311/detail/thumb_2311ba1c0a35-3b88-4371-b625-4dc484bb427f.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/10/13/se_2020a4dc3661-a64a-494b-9425-8430a8eaa4e2.gif', '프루테리언'),
       ('충북 영동 샤인머스캣', 14800, 100, '2022-10-01T10:11:16','2022-10-01T10:11:16', 'https://www.oasis.co.kr:48581/product/834/detail/thumb_8348c63ad7e-cb1b-4ceb-b4a0-257717417f5d.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/28/se_202218226bc2-0c52-4fcd-b807-0c8b8763f726.jpg', '프루테리언'),
       ('유기농 바나나', 4500, 100, '2022-10-01T10:11:18','2022-10-01T10:11:18', 'https://www.oasis.co.kr:48581/product/54014/detail/detail_54014_0_93ce6982-fb94-4334-b0bf-93904ca43df5.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/11/6/se_20205dc7e6c8-db62-4438-830d-e5f23b31a056.jpg', '프루테리언'),
       ('달콤한 멜론', 16500, 100, '2022-10-01T10:11:19','2022-10-01T10:11:19', 'https://www.oasis.co.kr:48581/product/1297/detail/thumb_12976cc3e19a-5276-4ca3-8bff-1be90302c1af.jpg', 'https://www.oasis.co.kr:48580//se/2018/9/12/se_4f598bf6-24c9-4136-a057-1c1febf7e4ff.jpg', '프루테리언'),
       ('아보카도', 14800, 100, '2022-10-01T10:12:16','2022-10-01T10:12:16', 'https://www.oasis.co.kr:48581/product/53046/detail/detail_53046_0_0a58990d-c6d8-407e-a2d7-ce6d93ce2926.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/17/se_202244ada563-a316-4cd9-a100-fa6dd1d4bc07.jpg', '프루테리언'),
       ('자몽', 3200, 100, '2022-10-01T10:13:16','2022-10-01T10:13:16', 'https://www.oasis.co.kr:48581/product/37958/detail/detail_37958_0_5b03bbfc-ac6b-4bc6-836c-ff98d0bfeed4.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/3/20/se_2020a05a201b-f42c-467a-9951-e3b413bf88e6.gif', '프루테리언'),
       ('제스프리 유기농 썬골드 키위', 7900, 100, '2022-10-01T10:14:16','2022-10-01T10:14:16', 'https://www.oasis.co.kr:48581/product/21097/detail/detail_21097_0_3289d04c-a9aa-4fba-bb95-d0c183bd1c8c.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/6/24/se_20208c65ea20-0b00-4a55-a2fb-5a928a370a3b.gif', '프루테리언'),
       ('국내산 거봉', 11800, 100, '2022-10-01T10:15:16','2022-10-01T10:15:16', 'https://www.oasis.co.kr:48581/product/23635/detail/detail_23635_0_adefbf05-94a1-46fd-89a8-6241504a2107.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/6/4/se_2021ed76c205-74eb-4645-9195-2cc56b20710a.jpg', '프루테리언'),
       ('파인애플', 5460, 100, '2022-10-01T10:17:16','2022-10-01T10:17:16', 'https://www.oasis.co.kr:48581/product/4023/detail/thumb_402352059f82-4662-4c73-af79-eecc4018816b.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/6/26/se_202029484bed-e683-4ac2-8b4c-f3de351f1ac5.gif', '프루테리언'),
       ('유러피안 조이 샐러드', 2300, 100, '2022-10-09T07:11:11', '2022-10-09T07:11:11', 'https://www.oasis.co.kr:48581/product/25383/detail/detail_25383_0_f81d6871-e1ae-44b4-a5e4-342fb900766b.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/6/24/se_2021ac72732d-0252-410c-9dbd-1122110d441d.jpg', '비건'),
       ('미역국수', 2300, 100, '2022-10-09T08:11:11', '2022-10-09T08:11:11', 'https://www.oasis.co.kr:48581/product/24590/detail/detail_24590_0_20a921d2-f9b8-4044-8ee9-5e71c246bc7a.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/6/15/se_20219b1ec9b9-ad3a-4641-950c-ebc1c82df404.jpg', '비건'),
       ('바로먹는 톳샐러드', 3000, 100, '2022-10-09T09:11:11', '2022-10-09T09:11:11', 'https://www.oasis.co.kr:48581/product/9846/detail/detail_9846_0_2f164db6-9466-4697-b720-408e759a4e53.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/1/21/se_202154d08160-09f4-4db2-a2a7-9c2ccd7b1e3e.jpg', '비건'),
       ('맑은탕 버섯 순두부찌개', 7000, 100, '2022-10-09T07:19:11', '2022-10-09T07:19:11', 'https://www.oasis.co.kr:48581/product/8022/detail/detail_8022_0_f15e7ba2-754d-4557-81ca-6402a5ca58a6.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/11/17/se_202028200871-49f7-4f25-88bc-9728b3be4a34.jpg', '비건'),
       ('양송이 버섯죽', 3400, 100, '2022-10-09T07:11:17', '2022-10-09T07:11:17', 'https://www.oasis.co.kr:48581/product/3472/detail/thumb_3472fd2e3220-e784-4864-b0a0-8487f1ce26f3.jpg', 'https://www.oasis.co.kr:48580//se/2019/10/29/se_f85bc621-6b69-48da-ad00-cded6efd96b1.jpg', '비건'),
       ('아삭한 파프리카 샐러드', 2000, 100, '2022-10-09T07:20:11', '2022-10-09T07:20:11', 'https://www.oasis.co.kr:48581/product/12035/detail/detail_12035_0_466346c2-260b-4584-9847-1b202aa7372f.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/2/26/se_2021f26a7474-d1c0-4520-844a-62b620dfc7de.jpg', '비건'),
       ('오아시스 땡초부추전', 5000, 100, '2022-10-09T07:12:11', '2022-10-09T07:12:11', 'https://www.oasis.co.kr:48581/product/53091/detail/detail_53091_0_9de5cd2d-126b-4e38-a109-308ec24c3215.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/17/se_202215d32652-ae13-461e-b595-bfbd96483561.jpg', '비건'),
       ('검정콩 통곡물 선식', 7500, 100, '2022-10-09T07:13:11', '2022-10-09T07:13:11', 'https://www.oasis.co.kr:48581/product/41151/detail/detail_41151_0_42ba6178-82d1-4ca5-9307-f1db1f30f36b.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/2/4/se_20226dbe31a0-8f9f-4ca3-b6c8-54b90fec442d.jpg', '비건'),
       ('산마늘 명이절임', 5000, 100, '2022-10-09T06:11:11', '2022-10-09T06:11:11', 'https://www.oasis.co.kr:48581/product/5348/detail/detail_5348_0_d12acb1d-674d-494b-9d0c-e89d088ac3cb.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/10/14/se_20201545618f-6800-4449-98b1-b7b595a76a65.gif', '비건'),
       ('유기농 즙용 케일', 4200, 100, '2022-10-07T07:11:11', '2022-10-07T07:11:11', 'https://www.oasis.co.kr:48581/product/4426/detail/thumb_44264e3559dd-deeb-472b-bd39-b22a2377f458.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/4/14/se_2020d9a1caa5-2266-4138-a287-2cd132b010d6.gif', '비건'),
    ('야채 계란찜', 3800, 100, '2022-10-11T11:38:10','2022-10-11T11:38:10', 'https://www.oasis.co.kr:48581/product/7915/detail/detail_7915_0_9bcaeda9-da06-4931-a807-aeeebf53ea80.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/11/23/se_202014783695-45fa-4507-a403-ef33aa187c7d.jpg', '오보'),
    ('짜먹는 명란', 8400, 100, '2022-10-11T11:40:10','2022-10-11T11:40:10', 'https://www.oasis.co.kr:48581/product/4255/detail/thumb_4255bcc02709-f987-4d3c-a059-040ed28e03a3.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/9/7/se_202026d2597b-7eb3-48ad-947e-be840389165d.gif', '오보'),
    ('슈퍼막셰 바나나푸딩', 7800, 100, '2022-10-11T11:32:10','2022-10-11T11:32:10', 'https://www.oasis.co.kr:48581/product/49506/detail/detail_49506_0_054227f7-072b-487b-9c4c-7e3a474da7de.jpg', 'https://gi.esmplus.com/ineight/%EC%8A%88%ED%8D%BC%EB%A7%89%EC%85%B0_%EB%B0%94%EB%82%98%EB%82%98%ED%91%B8%EB%94%A9/%EB%B0%94%EB%82%98%EB%82%98%ED%91%B8%EB%94%A9-vert.jpg', '오보'),
    ('우리밀 계란과자', 2300, 100, '2022-10-10T11:38:10','2022-10-10T11:38:10', 'https://www.oasis.co.kr:48581/product/2093/detail/thumb_2093c8ef0a3d-2a27-47fe-8ae8-243e40e54cfc.jpg', 'https://www.oasis.co.kr:48580//se/2020/2/26/se_584ce449-01fb-455f-8263-fec577f5093f.gif', '오보'),
    ('짜지않고 촉촉한 반숙계란', 14900, 100, '2022-10-11T12:38:10','2022-10-11T12:38:10', 'https://www.oasis.co.kr:48581/product/5469/detail/detail_5469_0_36bc2c5e-f0f8-43cf-8272-29ef6fdca3c5.jpg', 'http://gi.esmplus.com/ecohoyu/20_1.jpg', '오보'),
    ('맥반석 구운계란', 20900, 100, '2022-10-11T11:48:10','2022-10-11T11:48:10', 'https://www.oasis.co.kr:48581/product/36916/detail/detail_36916_0_25a79e9f-a42f-4521-9e42-bd88ab73028a.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/14/se_202193c96c05-06e0-4b8f-9901-2d015c6aeda7.jpg', '오보'),
    ('정갈한 명란젓', 13900, 100, '2022-10-11T11:58:10','2022-10-11T11:58:10', 'https://www.oasis.co.kr:48581/product/2306/detail/detail_2306_0_c7c21c1d-20a0-4268-b79f-17ae30b348bb.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/27/se_2021592cb49f-997c-4066-ac23-2ccad165bf91.jpg', '오보'),
    ('샘고을 명란젓 무침', 21900, 100, '2022-10-11T09:38:10','2022-10-11T09:38:10', 'https://www.oasis.co.kr:48581/product/21081/detail/detail_21081_0_e6815420-1b7d-4f99-91ab-dff62512029e.jpg', 'https://shelko.hgodo.com/jsmg/mm/a_1.jpg', '오보'),
    ('하동녹차 명란김세트', 24500, 100, '2022-08-11T11:38:10','2022-08-11T11:38:10', 'https://www.oasis.co.kr:48581/product/48805/detail/detail_48805_0_73253ffd-aafc-43ed-a8cb-6389ce169d1c.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/7/27/se_20221767a63c-2e87-4551-934d-886eaceba17f.jpg', '오보'),
    ('깐 메추리알', 12000, 100, '2022-09-11T11:38:10','2022-09-11T11:38:10', 'https://www.oasis.co.kr:48581/product/35142/detail/detail_35142_0_7d0a34d1-ece4-4181-a5f9-0ef4c18892f8.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/10/28/se_2021605a90b1-9bd0-48f5-8f8a-419d404e405d.jpg', '오보'),
    ('감자 치즈볼', 10700, 100, '2022-09-13T11:25:10','2022-09-13T11:25:10', 'https://www.oasis.co.kr:48581/product/6896/detail/detail_6896_0_fd0aa659-6acb-4a30-81fe-8d081954a97b.jpg','https://oasis.co.kr:9888/view/se/?imagePath=2020/9/15/se_2020b9e2921f-0666-4cdc-b229-b90aad21199c.gif', '락토'),
    ('단호박 우유 수프', 3900, 100, '2022-09-16T11:25:10','2022-09-16T11:25:10', 'https://www.oasis.co.kr:48581/product/25368/detail/detail_25368_0_4209bc77-6a43-466c-8208-b0a0e7c2585a.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/6/23/se_2021744b4ee8-9c3e-4ef8-903b-132a22bbe766.jpg', '락토'),
    ('코우카키스 무지방 그릭요거트', 4500, 100, '2022-09-13T10:25:10','2022-09-13T10:25:10', 'https://www.oasis.co.kr:48581/product/38446/detail/detail_38446_0_399d993b-799a-45a0-b3d4-24e0783d963a.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/17/se_2021083afbb2-b5f8-44d7-9c53-ad665aff70c5.jpg', '락토'),
    ('수제 우유 꿀설기떡', 7000, 100, '2022-09-13T11:30:10','2022-09-13T11:30:10', 'https://www.oasis.co.kr:48581/product/7935/detail/detail_7935_0_d27df98f-f7fe-4668-b825-6581fff6a299.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/11/11/se_20209de4bc89-540e-4eab-be72-b72ddd545c64.jpg', '락토'),
    ('르갈 딸기 크림치즈', 5900, 100, '2022-09-13T11:45:10','2022-09-13T11:45:10', 'https://www.oasis.co.kr:48581/product/7480/detail/detail_7480_0_adaeef27-873d-4dd2-96ed-5d28b8aa12af.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/10/27/se_2020c7f184da-c85e-469d-99ac-5f471a1f4d6c.jpg', '락토'),
    ('우리밀 와플', 8300, 100, '2022-09-13T11:55:10','2022-09-13T11:55:10', 'https://www.oasis.co.kr:48581/product/11720/detail/detail_11720_0_dc06d9c6-23d5-403a-8d3c-cba666aabeeb.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/2/24/se_202172847ffd-11be-452b-aca7-2cfd2ee3dedc.jpg', '락토'),
    ('르갈 갈릭 허브 크림치즈', 5900, 100, '2022-09-14T11:25:10','2022-09-14T11:25:10', 'https://www.oasis.co.kr:48581/product/7479/detail/detail_7479_0_75199341-b91c-4568-8e0f-72c2f0ab8b54.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/10/27/se_202015e100e6-f1d3-4ea7-95fe-72f8a82c0197.jpg', '락토'),
    ('ELF 과일치즈', 4800, 100, '2022-09-12T11:25:10','2022-09-12T11:25:10', 'https://www.oasis.co.kr:48581/product/3530/detail/thumb_35300e12dad5-5bc3-4e1a-bc77-674136f9dc47.jpg', 'https://www.oasis.co.kr:48580//se/2019/4/23/se_39b3a26a-c398-4069-b894-2a245d2fd113.jpg', '락토'),
    ('구워먹는 치즈바 흑임자', 8900, 100, '2022-09-03T11:25:10','2022-09-03T11:25:10', 'https://www.oasis.co.kr:48581/product/56122/detail/detail_56122_0_7efe2d23-3688-4196-b65d-9dce63484a1a.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/10/4/se_2022590ce667-1e30-4f36-8720-cea792934e2a.jpg', '락토'),
    ('리코타치즈 단호박 샐러드랩', 5900, 100, '2022-10-13T11:25:10','2022-10-13T11:25:10', 'https://www.oasis.co.kr:48581/product/54379/detail/detail_54379_0_b02c895f-6cfc-492e-b0b4-8f903e7e1f74.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/31/se_2022e9b78d4b-4994-4486-a36c-81009474c983.jpg', '락토'),
    ('펭귄 수제화덕피자 마농 세떼치즈', 14900, 100, '2022-10-20T03:42:10', '2022-10-20T03:42:10', 'https://www.oasis.co.kr:48581/product/40843/detail/detail_40843_0_2b2f84c5-2c8f-4f35-b8a9-9f356b30f0c8.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/2/3/se_2022350a8444-6ff7-4d0f-a833-597b7f0a0150.jpg', '락토-오보'),
    ('펭귄 수제화덕피자 마르게리따', 11900, 100, '2022-10-20T03:42:11', '2022-10-20T03:42:11', 'https://www.oasis.co.kr:48581/product/40841/detail/detail_40841_0_ac26293f-a6c0-4d0d-b6e0-5a259f8102f3.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/1/24/se_2022f3b25a63-1b80-4fc5-9b33-cfebd46672a0.jpg', '락토-오보'),
    ('블랙 쌀식빵 트리플치즈', 4700, 100, '2022-10-21T03:42:10', '2022-10-21T03:42:10', 'https://www.oasis.co.kr:48581/product/38864/detail/detail_38864_0_f859ff7b-66ef-43ea-b892-84587a26191b.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/1/13/se_20227895e90c-d824-4518-a7bf-f501596ab937.jpg', '락토-오보'),
    ('미니 까망베르 치즈 케이크', 25000, 100, '2022-10-12T03:42:10', '2022-10-12T03:42:10', 'https://www.oasis.co.kr:48581/product/38505/detail/detail_38505_0_3978c820-0209-49a6-8825-3faadff0a8a0.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/20/se_2021608ca695-2da2-4fc3-82c8-e467a3911f56.gif', '락토-오보'),
    ('애슐리 치즈케이스 딥디쉬피자', 7900, 100, '2022-10-02T03:42:10', '2022-10-02T03:42:10', 'https://www.oasis.co.kr:48581/product/54205/detail/detail_54205_0_10305612-61e9-4586-a684-43b3b508f7b5.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/30/se_2022655c2423-17b7-4134-b2db-c0323753c3a0.jpg', '락토-오보'),
    ('다사로이 크림치즈 팔쪽 마늘바게트볼', 16000, 100, '2022-10-03T03:42:10', '2022-10-03T03:42:10', 'https://www.oasis.co.kr:48581/product/35756/detail/detail_35756_0_c007a8e6-a5b8-45ef-b959-59f98e5f6a5c.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/10/29/se_202144d93c5a-dd88-448b-b879-84a4baa98efd.jpg', '락토-오보'),
    ('라이스 크로아상 생지', 12000, 100, '2022-10-01T03:42:10', '2022-10-01T03:42:10', 'https://www.oasis.co.kr:48581/product/21257/detail/detail_21257_0_6d93ba86-a136-4c21-8277-a017901bed14.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/5/21/se_2021bb4ef606-50c6-4e38-808c-a821bd5bc8c2.jpg', '락토-오보'),
    ('수제 단팥빵', 9900, 100, '2022-08-20T03:42:10', '2022-08-20T03:42:10', 'https://www.oasis.co.kr:48581/product/47074/detail/detail_47074_0_9e11052c-0c59-47ce-b9a6-de299dd2aac3.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/5/3/se_202236cc9752-1704-48ce-a479-bf8be693e74b.jpg', '락토-오보'),
    ('커스터드 공주밤식빵', 8500, 100, '2022-09-28T11:40:10', '2022-09-28T11:40:10', 'https://www.oasis.co.kr:48581/product/38499/detail/detail_38499_0_eb98d189-2400-4e87-b2c2-b9731bd00d55.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/20/se_2021b2ba2265-76f8-4904-9ef4-be0d4994d037.jpg', '락토-오보'),
    ('홍차 카스텔라', 25000, 100, '2022-08-20T03:42:10', '2022-08-20T03:42:10', 'https://www.oasis.co.kr:48581/product/38497/detail/detail_38497_0_20b02db4-9d7c-4d6b-a568-360476f64460.jp', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/20/se_20215763a7d8-7fb7-4df6-9340-5299291fdf03.jpg', '락토-오보'),
    ('유기농 고추장닭갈비', 2500, 100, '2022-09-28T11:40:10','2022-09-28T11:40:10', 'https://www.oasis.co.kr:48581/product/13592/detail/detail_13592_0_f22a6926-094d-40f9-80f6-408daca4f96e.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/3/12/se_2021ec449b61-b771-45e0-91a8-a4c8f50b53f3.jpg', '폴로'),
    ('미인계 닭가슴살 스테이크', 5700, 100, '2022-09-27T11:40:10','2022-09-27T11:40:10', 'https://www.oasis.co.kr:48581/product/3210/detail/detail_3210_0_93f74b8a-3700-4bb2-8698-fd70ae9da45e.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/10/15/se_2020c88d0037-6762-44d8-8943-bd3648ae5bc6.gif', '폴로'),
    ('바사삭 치킨 윙', 8980, 100, '2022-09-28T12:40:10','2022-09-28T12:40:10', 'https://www.oasis.co.kr:48581/product/26540/detail/detail_26540_0_f2b11116-692c-4804-ab72-83e4fe393a15.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/7/8/se_2021d7e91218-ab23-4125-a649-9d217b8e1a6b.jpg', '폴로'),
    ('유기농 닭볶음탕', 18500, 100, '2022-09-28T11:10:10','2022-09-28T11:10:10', 'https://www.oasis.co.kr:48581/product/13586/detail/detail_13586_0_50dc4260-9d36-4420-aa58-ab8611b57bdc.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/3/12/se_202108c7ea33-7843-4308-953d-319f2209a05c.jpg', '폴로'),
    ('유자바질 닭가슴살 샐러드', 4900, 100, '2022-09-28T11:40:30','2022-09-28T11:40:30', 'https://www.oasis.co.kr:48581/product/35642/detail/detail_35642_0_0657de91-79d7-4bd5-b276-20bfe5465077.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/10/28/se_2021c64bd6bb-b152-468a-8f58-b0cb07ec34b3.jpg', '폴로'),
    ('데리야끼 닭가슴살 호밀 샌드위치', 4900, 100, '2022-09-18T11:40:10','2022-09-18T11:40:10', 'https://www.oasis.co.kr:48581/product/17001/detail/detail_17001_0_68678b68-29cf-4504-b553-77e40dea935a.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/4/7/se_202134bfc642-beaa-4489-b025-bec20833b512.jpg', '폴로'),
    ('무항생제 오리 간장주물럭', 12400, 100, '2022-09-22T11:40:10','2022-09-22T11:40:10', 'https://www.oasis.co.kr:48581/product/5089/detail/detail_5089_0_d89fee40-c5c2-4a2b-a6e6-f4522835c384.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/8/13/se_2020bca8aba7-7fa0-44c7-a665-36188303903b.gif', '폴로'),
    ('무항생제 자연공법 오리훈제', 16000, 100, '2022-09-12T11:40:10','2022-09-12T11:40:10', 'https://www.oasis.co.kr:48581/product/2623/detail/detail_2623_0_eb936494-0efd-4198-8962-740b8e45f013.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/3/2/se_20216eb04884-0b65-40ca-af76-5223ed374912.jpg', '폴로'),
    ('설성목장 닭가슴살 로제 토마토소스', 4100, 100, '2022-09-20T11:40:10','2022-09-20T11:40:10', 'https://www.oasis.co.kr:48581/product/39964/detail/detail_39964_0_5038c416-684b-46db-b864-97d0d47ada0e.jpg','https://oasis.co.kr:9888/view/se/?imagePath=2022/1/10/se_202264f5291b-b54a-4b3b-871c-fab2f0d4f258.jpg', '폴로'),
    ('은은한 소이소스 닭가슴살 샐러드', 6490, 100, '2022-09-15T11:40:10','2022-09-15T11:40:10', 'https://www.oasis.co.kr:48581/product/3712/detail/detail_3712_0_264209ef-7548-4e8d-b955-b6d33ce208af.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/6/21/se_2022305cc5ef-b293-4836-8ac4-e5305e0b9290.jpg', '폴로'),
    ('꽈리고추 멸치볶음', 5800, 100, '2022-09-13T09:09:10', '2022-09-13T09:09:10' , 'https://www.oasis.co.kr:48581/product/4666/detail/detail_4666_0_2971333d-456e-404f-b24f-8e37eef44518.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/1/14/se_202193baa293-d017-409b-b049-82803a5115c6.gif', '페스코'),
    ('초이스쿡 전복 미역국', 8900, 100, '2022-09-11T09:09:10', '2022-09-11T09:09:10' , 'https://www.oasis.co.kr:48581/product/26203/detail/detail_26203_0_c85e6484-a46e-4d2e-ae42-08cf61541dda.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/7/9/se_2021e9b96516-3715-4790-811a-b6329d09c860.jpg', '페스코'),
    ('멸치칼국수', 5000, 100, '2022-09-03T09:09:10', '2022-09-03T09:09:10' , 'https://www.oasis.co.kr:48581/product/48373/detail/detail_48373_0_04fde823-4adf-4020-be6b-4f2c332e850d.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/5/23/se_20221310c27f-16bc-41d7-b7c3-792a5267589f.jpg', '페스코'),
    ('국내산 바지락', 7500, 100, '2022-08-13T09:09:10', '2022-08-13T09:09:10' , 'https://www.oasis.co.kr:48581/product/19418/detail/detail_19418_0_44f835ce-adf6-4732-a3d4-94c4e10ffc2b.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/4/27/se_20213f74d891-c3b8-41c5-870e-d90667893d36.jpg', '페스코'),
    ('새우 멘보샤 프라이', 10900, 100, '2022-09-23T09:09:10', '2022-09-23T09:09:10' ,'https://www.oasis.co.kr:48581/product/7008/detail/detail_7008_0_e932c669-62a3-4a89-bd2c-57bfc5a7d3d4.jpg','https://oasis.co.kr:9888/view/se/?imagePath=2020/11/17/se_202092be7f50-d860-47f5-a06b-9f1541e4ecf3.gif', '페스코'),
    ('완도 활전복', 32000, 100, '2022-09-13T09:09:11', '2022-09-13T09:09:11' , 'https://www.oasis.co.kr:48581/product/18712/detail/detail_18712_0_08a115c7-640a-4c21-831f-9dde9b49c326.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/9/8/se_2021e73d0496-ef47-4173-97ea-69fdf07ebbf2.jpg', '페스코'),
    ('포항 간편 해물탕세트', 26600, 100, '2022-08-13T09:09:10', '2022-08-13T09:09:10' , 'https://www.oasis.co.kr:48581/product/49272/detail/detail_49272_0_7b0e4714-0e84-488b-99df-b73d32fdf85f.JPG', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/6/13/se_202247bba3fd-4440-48e1-a380-202e2931479b.png', '페스코'),
    ('숫꽃게 양념게장', 38600, 100, '2022-09-19T09:09:10', '2022-09-19T09:09:10' , 'https://www.oasis.co.kr:48581/product/40252/detail/detail_40252_0_2e226588-d858-451b-bd98-ff8a49fcf268.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/1/12/se_2022f9dd9867-f09e-4c04-9ae2-2d35cbc92b97.jpg', '페스코'),
    ('지동관 깐쇼새우', 14900, 100, '2022-09-14T09:09:10', '2022-09-14T09:09:10' , 'https://www.oasis.co.kr:48581/product/32949/detail/detail_32949_0_e8689668-b45f-4c6a-a71f-b2952d2a1bac.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/9/7/se_2021facafe68-2716-4a15-9ea9-e029809a4a42.jpg', '페스코'),
    ('통통살 오징어볼', 7900, 100, '2022-09-01T09:09:10', '2022-09-01T09:09:10' , 'https://www.oasis.co.kr:48581/product/36064/detail/detail_36064_0_c09de047-1db5-409d-98b1-150806c2c489.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/11/5/se_20219746c4c4-0a71-4469-87fe-10260f7ca439.jpg', '페스코'),
    ('나시고랭 커리닭가슴살 ', 4100, 100, '2022-09-01T15:13:11', '2022-09-01T15:13:11', 'https://www.oasis.co.kr:48581/product/3691/detail/thumb_36910fb87a4e-21ae-41fe-af12-8af635306bea.jpg', 'https://www.oasis.co.kr:48580//se/2019/5/27/se_cd41086c-ba26-4f3e-9e0c-a1b2e8b33bb4.jpg', '폴로-페스코'),
    ('미나미 카모소바', 16900, 100, '2022-09-01T16:13:11', '2022-09-01T16:13:11', 'https://www.oasis.co.kr:48581/product/53152/detail/detail_53152_0_3fe780a4-3b39-4116-9633-dd17a71eec53.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/19/se_20225793adef-bca3-43e8-ae27-ac477071a7f3.jpg', '폴로-페스코'),
    ('국내산 닭다리 통살 유린기', 11000, 100, '2022-09-11T15:13:11', '2022-09-11T15:13:11', 'https://www.oasis.co.kr:48581/product/36218/detail/detail_36218_0_ec8a210c-cc03-497a-a3a0-0fab1a225622.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/11/10/se_20214fa4ebe4-8ba8-428e-a80f-19a202d69939.jpg', '폴로-페스코'),
    ('똠양꿍 ', 16900, 100, '2022-09-18T15:13:11', '2022-09-18T15:13:11', 'https://www.oasis.co.kr:48581/product/35862/detail/detail_35862_0_9ca6a65c-cbf6-4390-968b-7537d401d792.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/11/2/se_20214a6ec0b0-1dda-4f92-a2e0-b7b565086a08.jpg', '폴로-페스코'),
    ('치킨데리야끼 볶음밥 ', 2600, 100, '2022-09-03T15:13:11', '2022-09-03T15:13:11', 'https://www.oasis.co.kr:48581/product/19431/detail/detail_19431_0_9f36dad3-ae70-4b4d-a4c8-a5ed639f1607.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/4/28/se_20217322a690-9e84-4f2d-b2ee-519accc8ea8e.gif', '폴로-페스코'),
    ('백짬뽕 ', 13900, 100, '2022-09-02T15:13:11', '2022-09-02T15:13:11', 'https://www.oasis.co.kr:48581/product/32910/detail/detail_32910_0_44c1246c-8953-4b18-93a4-ff779ce2bc7c.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/9/7/se_202185fd907a-b618-4cec-bac1-c044a1c7cf0d.jpg', '폴로-페스코'),
    ('팔진 해물탕면', 12900, 100, '2022-09-21T15:13:11', '2022-09-21T15:13:11', 'https://www.oasis.co.kr:48581/product/49323/detail/detail_49323_0_202a979d-bf82-4d5a-bcc0-dcb85d96f205.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/6/14/se_2022e41598d6-d709-4a9a-baac-568e386beffb.jpg', '폴로-페스코'),
    ('국내산 전복닭죽 ', 28900, 100, '2022-09-17T15:13:11', '2022-09-17T15:13:11', 'https://www.oasis.co.kr:48581/product/50597/detail/detail_50597_0_91cb2c9c-1e5e-49e6-9491-50bf6d672489.jpg', 'https://gi.esmplus.com/eat2heaven/%ED%95%B4%EB%8B%B4%EC%9D%80/%EC%A0%84%EB%B3%B5%EB%8B%AD%EC%A3%BD.jpg', '폴로-페스코'),
       ('대게 삼계탕', 19900, 100, '2022-09-01T17:13:11', '2022-09-01T17:13:11', 'https://www.oasis.co.kr:48581/product/49969/detail/detail_49969_0_6673f00b-fce4-419e-86f6-9e9490f11a1e.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/6/27/se_20225b3d68e3-d18e-416a-97d4-dd1dabcf0d50.jpg', '폴로-페스코'),
       ('미나미 나또소바', 15000, 100, '2022-09-01T12:13:11', '2022-09-01T12:13:11', 'https://www.oasis.co.kr:48581/product/53153/detail/detail_53153_0_3311a161-85d2-4e58-abbd-3960761b56cf.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/19/se_2022c72839c6-8838-4b30-933c-905bad4bc854.jpg', '폴로-페스코'),
       ('설성목장 한우불고기 부리또', 4980, 100, '2022-10-02T20:28:10', '2022-10-02T20:28:10', 'https://www.oasis.co.kr:48581/product/48772/detail/detail_48772_0_967d43be-4e32-4aec-b028-b1e5d1975a2c.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/5/30/se_2022964385bb-69e5-435b-89a1-95a0be1525c8.jpg', '플렉시테리언'),
       ('소고기 해장국', 7800, 100, '2022-10-02T21:28:10', '2022-10-02T21:28:10', 'https://www.oasis.co.kr:48581/product/53087/detail/detail_53087_0_428187e4-787d-44e6-b197-fa285cf73996.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/17/se_2022a8e71b31-c1e4-44bd-a334-e7940888f7eb.jpg', '플렉시테리언'),
       ('불고기 꽃만두전골 밀키트', 18000, 100, '2022-10-03T20:28:10', '2022-10-03T20:28:10', 'https://www.oasis.co.kr:48581/product/50091/detail/detail_50091_0_101f8ed4-8a39-49be-b189-1773a5d83a8c.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/6/29/se_202232cbf93a-f632-46e2-8540-07c4d6d4a8a1.jpg', '플렉시테리언'),
       ('오모가리 수제 김치찌개', 5700, 100, '2022-10-02T20:28:11', '2022-10-02T20:28:11', 'https://www.oasis.co.kr:48581/product/51507/detail/detail_51507_0_4d92ff46-2b44-4cfa-b285-8c282b7c3e70.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2022/7/25/se_2022041625fc-8601-44b1-852d-906a850a541e.jpg', '플렉시테리언'),
       ('비프 살사 께사디야', 11000, 100, '2022-10-02T20:28:20', '2022-10-02T20:28:20', 'https://www.oasis.co.kr:48581/product/36302/detail/detail_36302_0_7ba71a79-7e5e-4cee-abd2-e7a3dfce58dc.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/11/16/se_2021b37f4b85-2dc7-40d3-802f-85384bce59af.jpg', '플렉시테리언'),
       ('감미옥 도가니탕', 18500, 100, '2022-10-02T20:38:10', '2022-10-02T20:38:10', 'https://www.oasis.co.kr:48581/product/38333/detail/detail_38333_0_53924791-cd57-448c-abc6-b037be8a8188.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/12/22/se_2021d231e109-b261-4193-a981-e19b3d5a5274.jpg', '플렉시테리언'),
       ('매콤순대 곱창볶음', 8500, 100, '2022-10-02T20:48:10', '2022-10-02T20:48:10', 'https://www.oasis.co.kr:48581/product/2965/detail/detail_2965_0_501d765e-c172-4a58-9f3e-037a195c54df.jpg', 'https://www.oasis.co.kr:48580//se/2018/10/4/se_060656dd-313c-4b44-9d62-f296e8309da9.jpg', '플렉시테리언'),
       ('통통등심 고구마 돈까스', 11000, 100, '2022-10-02T20:18:10', '2022-10-02T20:18:10', 'https://www.oasis.co.kr:48581/product/36508/detail/detail_36508_0_1379ff70-db2e-4069-837e-8ae0ca72af06.jpg', 'https://oasis.co.kr:9888/view/se/?imagePath=2021/11/17/se_2021ac0246ad-194f-4eab-b8e6-c56c122dd11c.jpg', '플렉시테리언'),
       ('수제짜장', 6000, 100, '2022-10-02T20:28:20', '2022-10-02T20:28:20', 'https://www.oasis.co.kr:48581/product/5246/detail/detail_5246_0_90db0a7e-cc4d-4790-af80-1a3fed9c1c4f.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/7/9/se_20207f29cd4f-2506-4da1-934c-66b383090e95.gif', '플렉시테리언'),
       ('소세지 야채볶음', 4500, 100, '2022-10-02T20:28:30', '2022-10-02T20:28:30', 'https://www.oasis.co.kr:48581/product/5540/detail/detail_5540_0_ed1c3ba8-d168-47c9-9ca7-a60eb3a2263f.gif', 'https://oasis.co.kr:9888/view/se/?imagePath=2020/7/30/se_2020776c925d-08c3-4238-8882-79e0af6c4406.gif', '플렉시테리언'),
       ('콤비네이션더블치즈 파니니', 9800, 100, '2022-10-02T10:28:10', '2022-10-02T10:28:10', 'https://www.oasis.co.kr:48581/product/53229/detail/detail_53229_0_ae84dfa9-2302-4f11-afcd-553a3d6fa711.jpg', 'https://www.oasis.co.kr:9888/view/se/?imagePath=2022/8/26/se_202218aa2938-0e3c-47a4-8147-795d9e616ce6.jpg', '플렉시테리언');
