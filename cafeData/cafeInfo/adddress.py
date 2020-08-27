import requests
from urllib.parse import urlparse
import pymysql
import time


# db connection
conn = pymysql.connect(host='i3a203.p.ssafy.io', user='root', password='password', db='dbName', charset='utf8')
try:
    with conn.cursor() as curs:
        sql = "SELECT * FROM cafe ORDER BY cafeno"
        curs.execute(sql)
        rs = curs.fetchall()

        update_sql = "UPDATE cafe SET lat=%s, lng=%s where address=%s"
        for row in rs:
            # row 하나씩 주소를 받아서 위 경도, 추출
            # row[3] = 주소
            address = row[3]
            url = 'https://dapi.kakao.com/v2/local/search/address.json?&query=' + address
            result = requests.get(urlparse(url).geturl(),
                                  headers={'Authorization': 'KakaoAK REST_API_KEY'}).json()

            if len(result['documents']) != 0:
                match_first = result['documents'][0]['address']
                lat = float(match_first['y'])
                lng = float(match_first['x'])
                print(lat, lng)  # 위도(lat) 경도(long)

                curs.execute(update_sql, (float(match_first['y']), float(match_first['x']), address))
    conn.commit()
finally:
    conn.close()
