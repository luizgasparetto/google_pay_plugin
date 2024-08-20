Google Pay Plugin - Flutter Integration

[![Flutter](https://img.shields.io/badge/Flutter-blue)](https://flutter.dev)
[![Android](https://img.shields.io/badge/Android-blue)](https://www.android.com/intl/pt_br/v)

## Overview

This repository provides an example of how to integrate Google Pay into a Flutter application using native Android code. The project demonstrates how to create a custom plugin to bridge communication between Flutter and native Android, enabling payments via Google Pay within your Flutter app.

## Features

### 1. Availability Check (`isReadyToPay`)

The native code implements an initial check to determine whether Google Pay is available on the user's device. This method is essential to ensure that the device supports Google Pay before attempting any transactions.

### 2. Payment Processing (`processPayment`)

The native code also includes a method to handle the payment process through Google Pay. This method initiates the payment request, handles the user's interaction with the Google Pay interface, and returns thepayment data to Flutter for further processing. This ensures seamless integration between the Flutter UI and the underlying payment gateway.
