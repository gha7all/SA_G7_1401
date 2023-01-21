import os
from os.path import isfile, join

from fpdf import FPDF

from matplotlib import rcParams

rcParams['axes.spines.top'] = False
rcParams['axes.spines.right'] = False


class PDF(FPDF):
    def __init__(self, format):
        super().__init__(format=format)

    def header(self):
        # Custom logo and positioning
        # Create an `assets` folder and put any wide and short image inside
        # Name the image `logo.png`
        # self.image('assets/logo.png', 10, 8, 33)
        self.set_font('Arial', 'B', 11)
        self.set_text_color(76, 0, 153)
        self.cell(00, 1, 'Thresholds Report', 0, 0, 'R')
        self.ln(20)

    def footer(self):
        # Page numbers in the footer
        self.set_y(-15)
        self.set_font('Arial', 'I', 8)
        self.set_text_color(76, 0, 153)
        self.cell(0, 10, 'Page ' + str(self.page_no()), 0, 0, 'C')

    def page_body(self, images, des):
        if len(images) == 3:
            self.set_font('Arial', size=11)
            self.multi_cell(0, 5, des)

            self.image(images[0], 15, 60, 150)
            self.image(images[1], -20, 300, 260)
            self.image(images[2], 0, 170, 210)
        else:
            self.image(images[0], 30, 30, 150)

    def print_page(self, images, des):
        print("print_page")
        # Generates the report
        self.add_page()
        self.page_body(images, des)


def construct(PLOT_DIR='outputs'):
    pages_data = []
    temp = []
    # Get all plots
    files = [f for f in os.listdir(PLOT_DIR) if isfile(join(PLOT_DIR, f))]
    # Iterate over all created visualization
    for file in files:
        temp.append(f'{PLOT_DIR}/{file}')

    return pages_data


def export_pdf(meta_data_of_results: [dict], metrics_image_path):
    pdf = PDF(format=(220, 400))

    for result in meta_data_of_results:
        images = [join(result["threshold_output_path"], f) for f in os.listdir(result["threshold_output_path"]) if
                  isfile(join(result["threshold_output_path"], f))]
        pdf.print_page(images, result["des"])

    pdf.print_page([metrics_image_path], "")

    pdf.output('SalesRepot.pdf', 'F')
