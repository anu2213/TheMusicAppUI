package com.anushka.themusicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.anushka.themusicappui.MainViewModel
import com.anushka.themusicappui.R
import com.anushka.themusicappui.ui.theme.*
import com.anushka.themusicappui.ui.theme.Screen.BottomScreen.Browse.title
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    //ALLOW US TO  FIND OUT WHICH "VIEW" WE CURRENTLY ARE
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember { mutableStateOf(false) }
    val currentScreen = viewModel.currentScreen.value
    val title = remember { mutableStateOf(currentScreen.title) }
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp

    ModalDrawer(  //Using Material2 ModalDrawer here
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                screensInDrawer = screensInDrawer,
                currentRoute = currentRoute,
                onItemClicked = { item ->
                    scope.launch { drawerState.close() }
                    if (item.dRoute == "add_account") {
                        dialogOpen.value = true
                    } else {
                        navController.navigate(item.dRoute)
                        title.value = item.dTitle
                    }
                }
            )
        }
    ) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
            sheetContent = {
                MoreBottomSheet()
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(title.value) },
                        actions={
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (bottomSheetState.isVisible) {
                                            bottomSheetState.hide()
                                        } else {
                                            bottomSheetState.show()
                                        }
                                    }

                                }
                            ) {
                                Icon(imageVector = Icons.Default. MoreVert, contentDescription = null)
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.AccountCircle, contentDescription = "Menu")
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomNavBar(navController, currentRoute,title)
                }
            ) {  pd ->
                Navigation(navController, viewModel, pd)
                AccountDialog(dialogOpen = dialogOpen)
            }
        }
    }
}

@Composable
fun DrawerContent(
    screensInDrawer: List<Screen.DrawerScreen>,
    currentRoute: String?,
    onItemClicked: (Screen.DrawerScreen) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(screensInDrawer) { item ->
            DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                onItemClicked(item)
            }
        }
    }
}

@Composable
fun DrawerItem(selected: Boolean, item: Screen.DrawerScreen, onDrawerItemClicked: () -> Unit) {
    val background = if (selected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .clickable { onDrawerItemClicked() }
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 16.dp, top = 2.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?,title: MutableState<String>) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.lightBlue)
    ) {
        screensInBottom.forEach { item ->
            val isSelected = currentRoute == item.bRoute
            val tint = if (isSelected) colorResource(id = R.color.magenta) else Color.Black

            BottomNavigationItem(
                selected = isSelected,
                onClick = { navController.navigate(item.bRoute)
                    title.value =item.bTitle
                          },
                icon = {
                    Icon(
                        tint = tint,
                        painter = painterResource(id = item.icon),
                        contentDescription = item.bTitle
                    )
                },
                label = { Text(item.bTitle, color = tint) }
            )
        }
    }
}

@Composable
fun MoreBottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = colorResource(id = R.color.lightBlue))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings"
                )
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription = "Share"
                )
                Text(
                    text = "Share",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.baseline_help_center_24),
                    contentDescription = "Help"
                )
                Text(
                    text = "Help",
                    fontSize = 20.sp,
                    color = Color.Black
                )

            }
        }
    }
}

    @Composable
    fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screen.DrawerScreen.Account.route,
            modifier = Modifier.padding(pd)
        ) {
            composable(Screen.BottomScreen.Home.bRoute) { Home() }
            composable(Screen.BottomScreen.Library.bRoute) { Library() }
            composable(Screen.BottomScreen.Browse.bRoute) { Browse() }
            composable(Screen.DrawerScreen.Account.route) { AccountView() }
            composable(Screen.DrawerScreen.subscription.route) { Subscription() }
        }
    }

