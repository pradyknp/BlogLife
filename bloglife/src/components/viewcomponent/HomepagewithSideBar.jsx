import React from 'react'

import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'

import Tableleft from './Tableleft'
import Tableright from './Tableright'

class HomepagewithSideBar extends React.Component {
// Each logical "route" has two components, one for
// the sidebar and one for the main area. We want to
// render both of them in different places when the
// path matches the current URL.

constructor(props){
    super(props);
    this.state={
        routes:[
          { path: '/',
            exact: true,
            sidebar: () => <div>View All!</div>,
            main: () => <Tableright categoryProps="getAll"/>
          },
          { path: '/category/Entertainment',
            sidebar: () => <div>bubblegum!</div>,
            main: () => <Tableright categoryProps="entertainment"/>
          },
           { path: '/category/politics',
            sidebar: () => <div>bubblegum!</div>,
             main: () => <Tableright categoryProps="politics"/>
         },
          { path: '/category/travel',
            sidebar: () => <div>shoelaces!</div>,
            main: () => <Tableright categoryProps="travel"/>
          },
          { path: '/category/history',
           sidebar: () => <div>bubblegum!</div>,
           main: () => <Tableright categoryProps="history"/>
          },
          { path: '/category/finance',
           sidebar: () => <div>bubblegum!</div>,
           main: () => <Tableright categoryProps="finance"/>
          },
          { path: '/category/healthcare',
            sidebar: () => <div>bubblegum!</div>,
            main: () => <Tableright categoryProps="healthcare"/>
           },
          { path: '/category/humanresources',
           sidebar: () => <div>bubblegum!</div>,
           main: () => <Tableright categoryProps="humanresources"/>
            },
           { path: '/category/business',
             sidebar: () => <div>bubblegum!</div>,
             main: () => <Tableright categoryProps="business"/>
           },
            { path: '/category/general',
                 sidebar: () => <div>bubblegum!</div>,
              main: () => <Tableright categoryProps="general"/>
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

         <b>Filter By Category</b><br/>
        <ul style={{ listStyleType: 'none', padding: '0px 0px 0px 10px' }}>

          <li className="category"><Link to="/">View All</Link></li><br/>
          <li className="category"><Link to="/category/entertainment">Entertainment</Link></li><br/>
          <li className="category"><Link to="/category/politics">Politics</Link></li><br/>
          <li className="category" ><Link to="/category/travel">Travel</Link></li><br/>
          <li className="category"><Link to="/category/history">History</Link></li><br/>
          <li className="category"><Link to="/category/finance">Finance</Link></li><br/>
          <li className="category"><Link to="/category/healthcare">HealthCare</Link></li><br/>
          <li className="category"><Link to="/category/humanresources">Human Resources</Link></li><br/>
          <li className="category"><Link to="/category/business">Business</Link></li><br/>
          <li className="category"><Link to="/category/general">General</Link></li><br/>


        </ul>

         <br/>
              <br/>
               <div className="input-group">
               <label htmlFor="general-search-search-input" className="isvishidden">Search the site</label>

               <input type="text" className="input-search" name="q" ref="general-search-search-input" placeholder="Search By Title"></input>

               <button className="btn btn-go">GO</button>

        </div>

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
export default HomepagewithSideBar