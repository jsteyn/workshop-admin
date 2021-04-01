import dash
import dash_html_components as html

external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets)

app.layout = html.Div(children=[
    html.H1(children='CarpenPi Admin'),

    html.Div(children='''
        An admin app for running CarpenPi workshops.
    ''')
])

if __name__ == '__main__':
    app.run_server(debug=True)
