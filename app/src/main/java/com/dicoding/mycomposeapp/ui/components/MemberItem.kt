package com.dicoding.mycomposeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mycomposeapp.R
import com.dicoding.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun MemberItem(
    @DrawableRes image: Int,
    name: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(80.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = name,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Text(
                text = description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberItemPreview() {
    MyComposeAppTheme {
        MemberItem(image = R.drawable.member_3, name = "Taeyong", description = "Lee Tae-yong (Hangul: 이태용), known by his mononym Taeyong, is a South Korean lead rapper, songwriter, lead dancer, choreographer, visual, and singer. He is the leader and member of the South Korean male vocal group NCT in the subunit NCT U and is the leader of NCT 127. He is also currently a member of another SM Entertainment vocal group, SuperM.")
    }
}