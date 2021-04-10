import requests
from bs4 import BeautifulSoup

def GetAllChapters(url):
    "Get all link of chapters"
    page = requests.get(url)

    soup = BeautifulSoup(page.text, 'lxml')
    soup = soup.find("ul", class_="scrollable-menu")
    soup = soup.find_all("a")

    list_href = []
    for item in soup:
        list_href.append(item["href"])

    return list_href

def GetContent(url):
    "Get content of each chapter"
    page = requests.get(url)

    soup = BeautifulSoup(page.text, "lxml")
    soup = soup.find_all('p')
    list_paragraphs = []
    for item in soup:
        element = item.contents[0]
        list_paragraphs.append(element)
    
    list_paragraphs.pop(0);
    list_paragraphs.pop(len(list_paragraphs) - 1)
    
    return '\n'.join(list_paragraphs)

def GenerateQuery(story, id, chapter, content):
    "Generate query to insert data"
    content = content.replace("'", '"')

    fo = open("query.sql", "a", encoding="utf-8")
    query = f"INSERT INTO Chapter VALUES ({story}, {id}, '{chapter}', '{content}');\n\n"
    fo.write(query)
    fo.close()

if __name__ == "__main__":
    url = "https://sachvui.com/doc-sach/mat-ma-da-vinci/chuong-mo-dau.html"
    list_href = GetAllChapters(url)

    index = 0
    for item in list_href:
        chapter = "Chương " + str(index)
        GenerateQuery(0, index, chapter, GetContent(item))
        print(f"{chapter} successfully!")
        index += 1