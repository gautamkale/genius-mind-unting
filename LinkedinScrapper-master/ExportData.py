import csv
import json

def exportData(res):
        csvfile = "Export.csv"

        x = json.loads(res)

        f = csv.writer(open("csvfile.csv", "wb+"))

        # Write CSV Header, If you dont need that, remove this line
        ##f.writerow(["name", "headline", "company", "school", "location","summary","company",])
        f.writerow(["personal_info", "experiences" ])

        for x in x:
            f.writerow([x["personal_info"],
                        x["experiences"]])