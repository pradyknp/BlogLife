import React from 'react'

import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'

import ViewContent from './ViewContent'

class ViewFrame extends React.Component {
// Each logical "route" has two components, one for
// the sidebar and one for the main area. We want to
// render both of them in different places when the
// path matches the current URL.

constructor(props){
    super(props);
    this.state={
        routes:[
          { path: '/BlogLife',
            exact: true,
            sidebar: () => <div><b>Filtered By View All</b></div>,
            main: () => <ViewContent categoryProps="getAll"/>
          },
          { path: '/BlogLife/category/Entertainment',
            sidebar: () => <div><b>Filtered By Entertainment</b></div>,
            main: () => <ViewContent categoryProps="entertainment"/>
          },
           { path: '/BlogLife/category/politics',
            sidebar: () => <div><b>Filtered By Politics</b></div>,
             main: () => <ViewContent categoryProps="politics"/>
         },
          { path: '/BlogLife/category/travel',
            sidebar: () => <div><b>Filtered By Travel</b></div>,
            main: () => <ViewContent categoryProps="travel"/>
          },
          { path: '/BlogLife/category/history',
           sidebar: () => <div><b>Filtered By History</b></div>,
           main: () => <ViewContent categoryProps="history"/>
          },
          { path: '/BlogLife/category/finance',
           sidebar: () => <div><b>Filtered By Finance</b></div>,
           main: () => <ViewContent categoryProps="finance"/>
          },
          { path: '/BlogLife/category/healthcare',
            sidebar: () => <div><b>Filtered By HealthCare</b></div>,
            main: () => <ViewContent categoryProps="healthcare"/>
           },
          { path: '/BlogLife/category/humanresources',
           sidebar: () => <div><b>Filter By Human Resources</b></div>,
           main: () => <ViewContent categoryProps="humanresources"/>
            },
           { path: '/BlogLife/category/business',
             sidebar: () => <div><b>Filter By Business</b></div>,
             main: () => <ViewContent categoryProps="business"/>
           },
            { path: '/BlogLife/category/general',
                 sidebar: () => <div><b>Filter By General</b></div>,
              main: () => <ViewContent categoryProps="general"/>
            },
            { path: '/BlogLife/myblogs',
                sidebar: () => <div><b>Filtered By MyBlogs</b></div>,
                main: () => <ViewContent categoryProps="getByUser" />
            },
            { path: '/BlogLife/category/title/*',
                sidebar: () => <div><b>Filtered By Title</b></div>,
                main: () => <ViewContent categoryProps="getByTitle" />
            },

        ]
    }
}


render(){

return(
<div>
  <Router>
    <div style={{ display: 'flex' }}>
      <div style={{
        padding: '10px',
        width: '25%',
        background: 'white'
      }}>

          {this.state.routes.map((route, index) => (
              // You can render a <Route> in as many places
              // as you want in your app. It will render along
              // with any other <Route>s that also match the URL.
              // So, a sidebar or breadcrumbs or anything else
              // that requires you to render multiple things
              // in multiple places at the same URL is nothing
              // more than multiple <Route>s.
              <Route
                  key={index}
                  path={route.path}
                  exact={route.exact}
                  component={route.sidebar}
              />
          ))}

        <ul style={{ listStyleType: 'none', padding: '0px 0px 0px 10px' }}>

          <li className="category"><Link to="/BlogLife">View All</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/entertainment">Entertainment</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/politics">Politics</Link></li><br/>
          <li className="category" ><Link to="/BlogLife/category/travel">Travel</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/history">History</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/finance">Finance</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/healthcare">HealthCare</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/humanresources">Human Resources</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/business">Business</Link></li><br/>
          <li className="category"><Link to="/BlogLife/category/general">General</Link></li><br/>


        </ul>

         <br/>
              <br/>
    {/*      <div className="input-group">
               <label htmlFor="general-search-search-input" className="isvishidden">Search the site</label>

               <input type="text" className="input-search" name="q" ref="general-search-search-input" placeholder="Search By Title"></input>

               <button className="btn btn-go">GO</button>

        </div>*/}

      </div>

      <div style={{ flex: 1, padding: '10px', 'minHeight':'700px','maxHeight':'900px' }}>
        {this.state.routes.map((route, index) => (
          // Render more <Route>s with the same paths as
          // above, but different components this time.
          <Route
            key={index}
            path={route.path}
            exact={route.exact}
            component={route.main}
          />
        ))}
      </div>
    </div>
  </Router>
  </div>
  )

}

}
export default ViewFrame