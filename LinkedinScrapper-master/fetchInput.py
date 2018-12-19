import xlrd


def fetch(fileName):
   ## fileName="B:/leadgenius/test.xlsx"

    searchList= []
    names= []
    sh = xlrd.open_workbook(fileName).sheet_by_index(0)
    for rownum in range(1,sh.nrows):
        print(rownum)
        print("rownum1: "+str(rownum)+"rownum2: "+ str(2) + "rownum4: "+ str(1))
        print("rownum1: " + str(rownum) + "rownum2: " + str(3) + "rownum4: " + str(5))
  ##      print(sh.cell(rownum, 3).value + " " + sh.cell(rownum, 5).value)

        tmp = u' '.join((sh.cell(rownum, 7).value,sh.cell(rownum, 6).value)).encode('utf-8').strip()
       ## name = u' '.join((sh.cell(rownum, 1).value,sh.cell(rownum, 5).value)).encode('utf-8').strip()
        name=sh.cell(rownum, 1).value;
        print(name)
        print(tmp)
        searchList.append(tmp)
        names.append(name)
    return searchList, names

### return [u' '.join((sh.cell(rownum, 2).value,sh.cell(rownum, 1).value)).encode('utf-8').strip() +'linkedin' for rownum in range(1,sh.nrows)]