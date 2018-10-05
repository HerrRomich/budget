import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatMenuModule
} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ApolloModule, APOLLO_OPTIONS } from 'apollo-angular';
import { HttpLink, HttpLinkModule } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AccountsModule } from './components/accounts/accounts.module';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './material.module';

export function createApollo(httpLink: HttpLink) {
  return {
    link: httpLink.create({ uri: '/accounts' }),
    cache: new InMemoryCache()
  };
}
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AccountsModule,
    HttpClientModule,
    ApolloModule,
    HttpLinkModule,
    MaterialModule,
    FlexLayoutModule
  ],
  providers: [
    {
      provide: APOLLO_OPTIONS,
      useFactory: createApollo,
      deps: [HttpLink]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
