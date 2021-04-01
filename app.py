import dash
import dash_core_components as dcc
import dash_html_components as html

external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets)

app.layout = html.Div(children=[
    dcc.Location(id='url', refresh=False),
    html.Div(children=[
        html.Img(
            src='https://raw.githubusercontent.com/CarpenPi/docs/main/logo/CarpenPi_1.png',
            style={'width': '150px', 'display': 'inline-block'}
        ),

        dcc.Link('Navigate to home', href='/', style={'padding': '20px', 'font-weight': 'bold', 'font-size': '120%', 'display': 'inline-block'}),
        dcc.Link('Attend a workshop', href='/signup', style={'padding': '20px', 'font-weight': 'bold', 'font-size': '120%', 'height': '100px', 'display': 'inline-block'}),
    ]),
    # content will be rendered in this element
    html.Div(id='page-content')
])

@app.callback(dash.dependencies.Output('page-content', 'children'),
              [dash.dependencies.Input('url', 'pathname')])
def display_page(pathname):
    if pathname == '/':
        return html.Div(id='page-content', children=[
            html.H1(children='CarpenPi Admin'),

            html.Div(children=[
                html.P(children='''
                    An admin app for running CarpenPi workshops.
                '''),

            ])
        ])
    elif pathname == '/signup':
        return html.Div(id='page-content', children=[
            html.H1(children='Signup Form'),

            html.Div(children=[
                html.P(children=[
                    dcc.Input(
                        id="input_name",
                        type='text',
                        placeholder="Name",
                    )]
                ),
                html.P(children=[
                    dcc.Input(
                        id='input_email',
                        type='email',
                        placeholder="Email address"
                    )]
                )

            ])
        ])
    else:
        return html.Div([
            html.H3('404 Not Found: '.format(pathname))
        ])


if __name__ == '__main__':
    app.run_server(debug=True)
