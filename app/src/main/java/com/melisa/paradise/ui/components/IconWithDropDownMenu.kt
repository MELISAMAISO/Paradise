package com.melisa.paradise.ui.components
// import androidx.compose.material.DropdownMenu
// import androidx.compose.material.DropdownMenuItem
// import androidx.compose.material.Icon
// import androidx.compose.material.IconButton
// import androidx.compose.material.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.vector.ImageVector
//
// data class MenuOption(
// val label: String,
// val action: () -> Unit
// )
//
// @Composable
// fun IconWithDropDownMenu(
// icon: ImageVector,
// menuOptions: String,
// isDropDownMenuExpanded: String,
// onDismissRequest: () -> Unit,
// onClick: () -> Unit,
// modifier: Modifier = Modifier,
// ) {
// IconButton(onClick = onClick) {
// Icon(
// modifier = modifier,
// imageVector = icon,
// contentDescription = ""
// )
// DropdownMenu(
// expanded = isDropDownMenuExpanded,
// onDismissRequest = onDismissRequest
// ) {
// for (menuOption in menuOptions) {
// DropdownMenuItem(
// onClick = menuOption.action,
// content = { Text(text = menuOption.label) }
// )
// }
// }
// }
// }
//