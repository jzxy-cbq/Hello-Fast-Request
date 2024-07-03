import {
  Admin,
} from "react-admin";
import {
  amplicodeDarkTheme,
  amplicodeLightTheme,
} from "./themes/amplicodeTheme/amplicodeTheme";
import { dataProvider } from "./dataProvider";
import { Resource, ListGuesser, EditGuesser, ShowGuesser } from "react-admin";

export const App = () => {
  return (
    <Admin
      dataProvider={dataProvider}
      lightTheme={amplicodeLightTheme}
      darkTheme={amplicodeDarkTheme}
    >
    
    <Resource name="accounts" 
        list={ListGuesser} edit={EditGuesser} show={ShowGuesser}
        
        recordRepresentation="name"
      />
</Admin>
  )
};
